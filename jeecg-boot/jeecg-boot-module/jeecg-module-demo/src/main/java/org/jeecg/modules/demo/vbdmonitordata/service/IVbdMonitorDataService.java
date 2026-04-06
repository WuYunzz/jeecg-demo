package org.jeecg.modules.demo.vbdmonitordata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorData;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IVbdMonitorDataService extends IService<VbdMonitorData> {
    public void saveMain(VbdMonitorData vbdMonitorData, List<VbdMonitorDetail> vbdMonitorDetailList);
    public void updateMain(VbdMonitorData vbdMonitorData, List<VbdMonitorDetail> vbdMonitorDetailList);
    public void delMain(String id);
    public void delBatchMain(Collection<? extends Serializable> idList);
}