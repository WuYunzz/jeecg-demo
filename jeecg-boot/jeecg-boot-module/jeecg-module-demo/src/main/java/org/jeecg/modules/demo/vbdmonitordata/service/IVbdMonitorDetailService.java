package org.jeecg.modules.demo.vbdmonitordata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;

import java.util.List;

/**
 * @Description: 试验数据记录明细表
 * @Author: Custom Generator
 * @Date:   2026-04-05
 * @Version: V1.0
 */
public interface IVbdMonitorDetailService extends IService<VbdMonitorDetail> {

    /**
     * 通过主表id查询子表数据
     *
     * @param mainId 主表id
     * @return List<VbdMonitorDetail>
     */
    public List<VbdMonitorDetail> selectByMainId(String mainId);
}