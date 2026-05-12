package org.jeecg.modules.demo.vbdmonitordata.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorData;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;
import org.jeecg.modules.demo.vbdmonitordata.service.IVbdMonitorDataService;
import org.jeecg.modules.demo.vbdmonitordata.service.IVbdMonitorDetailService;
import org.jeecg.modules.demo.vbdmonitordata.vo.VbdMonitorDataPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 病媒监测-耳剂药效试验主表
 * @Author: Custom Generator
 * @Date:   2026-04-05
 * @Version: V1.0
 */
@Tag(name="病媒监测-耳剂药效试验主表")
@RestController
@RequestMapping("/bmjc/vbdMonitorData")
@Slf4j
public class VbdMonitorDataController {

    @Autowired
    private IVbdMonitorDataService vbdMonitorDataService;
    @Autowired
    private IVbdMonitorDetailService vbdMonitorDetailService;

    /**
     * 分页列表查询
     */
    @Operation(summary="分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<VbdMonitorData>> queryPageList(VbdMonitorData vbdMonitorData,
                                                       @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                       @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                       HttpServletRequest req) {
        QueryWrapper<VbdMonitorData> queryWrapper = QueryGenerator.initQueryWrapper(vbdMonitorData, req.getParameterMap());
        Page<VbdMonitorData> page = new Page<>(pageNo, pageSize);
        IPage<VbdMonitorData> pageList = vbdMonitorDataService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     */
    @AutoLog(value = "病媒监测药效试验-添加")
    @Operation(summary="添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody VbdMonitorDataPage vbdMonitorDataPage) {
        VbdMonitorData vbdMonitorData = new VbdMonitorData();
        BeanUtils.copyProperties(vbdMonitorDataPage, vbdMonitorData);
        vbdMonitorDataService.saveMain(vbdMonitorData, vbdMonitorDataPage.getVbdMonitorDetailList());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     */
    @AutoLog(value = "病媒监测药效试验-编辑")
    @Operation(summary="编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody VbdMonitorDataPage vbdMonitorDataPage) {
        VbdMonitorData vbdMonitorData = new VbdMonitorData();
        BeanUtils.copyProperties(vbdMonitorDataPage, vbdMonitorData);
        VbdMonitorData vbdMonitorDataEntity = vbdMonitorDataService.getById(vbdMonitorData.getId());
        if(vbdMonitorDataEntity == null) {
            return Result.error("未找到对应数据");
        }
        vbdMonitorDataService.updateMain(vbdMonitorData, vbdMonitorDataPage.getVbdMonitorDetailList());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "病媒监测药效试验-通过id删除")
    @Operation(summary="通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        vbdMonitorDataService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "病媒监测药效试验-批量删除")
    @Operation(summary="批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.vbdMonitorDataService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过主表ID查询子表
     */
    @Operation(summary="通过主表ID查询子表")
    @GetMapping(value = "/queryDetailByMainId")
    public Result<List<VbdMonitorDetail>> queryDetailListByMainId(@RequestParam(name="id",required=true) String id) {
        List<VbdMonitorDetail> list = vbdMonitorDetailService.selectByMainId(id);
        return Result.OK(list);
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VbdMonitorData vbdMonitorData) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<VbdMonitorData> queryWrapper = QueryGenerator.initQueryWrapper(vbdMonitorData, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if(oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id",selectionList);
        }
        //Step.2 获取导出数据
        List<VbdMonitorData> vbdMonitorDataList = vbdMonitorDataService.list(queryWrapper);

        // Step.3 组装pageList
        List<VbdMonitorDataPage> pageList = new ArrayList<VbdMonitorDataPage>();
        for (VbdMonitorData main : vbdMonitorDataList) {
            VbdMonitorDataPage vo = new VbdMonitorDataPage();
            BeanUtils.copyProperties(main, vo);
            List<VbdMonitorDetail> vbdMonitorDetailList = vbdMonitorDetailService.selectByMainId(main.getId());
            vo.setVbdMonitorDetailList(vbdMonitorDetailList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "病媒监测药效试验列表");
        mv.addObject(NormalExcelConstants.CLASS, VbdMonitorDataPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("病媒监测药效试验数据", "导出人:"+sysUser.getRealname(), "药效试验数据", ExcelType.XSSF));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<VbdMonitorDataPage> list = ExcelImportUtil.importExcel(file.getInputStream(), VbdMonitorDataPage.class, params);
                for (VbdMonitorDataPage page : list) {
                    VbdMonitorData po = new VbdMonitorData();
                    BeanUtils.copyProperties(page, po);
                    vbdMonitorDataService.saveMain(po, page.getVbdMonitorDetailList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }
}