package org.jeecg.modules.demo.patientreportcard.controller;

import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.patientreportcard.entity.PatientReportCard;
import org.jeecg.modules.demo.patientreportcard.service.IPatientReportCardService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 患者报告卡
 * @Author: jeecg-boot
 * @Date:   2026-05-12
 * @Version: V1.0
 */
@Tag(name="患者报告卡")
@RestController
@RequestMapping("/patientreportcard/patientReportCard")
@Slf4j
public class PatientReportCardController extends JeecgController<PatientReportCard, IPatientReportCardService> {
	@Autowired
	private IPatientReportCardService patientReportCardService;
	
	/**
	 * 分页列表查询
	 *
	 * @param patientReportCard
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@Operation(summary="患者报告卡-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<PatientReportCard>> queryPageList(PatientReportCard patientReportCard,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {


        QueryWrapper<PatientReportCard> queryWrapper = QueryGenerator.initQueryWrapper(patientReportCard, req.getParameterMap());
		Page<PatientReportCard> page = new Page<PatientReportCard>(pageNo, pageSize);
		IPage<PatientReportCard> pageList = patientReportCardService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param patientReportCard
	 * @return
	 */
	@AutoLog(value = "患者报告卡-添加")
	@Operation(summary="患者报告卡-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody PatientReportCard patientReportCard) {
		patientReportCardService.save(patientReportCard);

		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param patientReportCard
	 * @return
	 */
	@AutoLog(value = "患者报告卡-编辑")
	@Operation(summary="患者报告卡-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody PatientReportCard patientReportCard) {
		patientReportCardService.updateById(patientReportCard);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "患者报告卡-通过id删除")
	@Operation(summary="患者报告卡-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		patientReportCardService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "患者报告卡-批量删除")
	@Operation(summary="患者报告卡-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.patientReportCardService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@Operation(summary="患者报告卡-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<PatientReportCard> queryById(@RequestParam(name="id",required=true) String id) {
		PatientReportCard patientReportCard = patientReportCardService.getById(id);
		if(patientReportCard==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(patientReportCard);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param patientReportCard
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PatientReportCard patientReportCard) {
        return super.exportXls(request, patientReportCard, PatientReportCard.class, "患者报告卡");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PatientReportCard.class);
    }

}
