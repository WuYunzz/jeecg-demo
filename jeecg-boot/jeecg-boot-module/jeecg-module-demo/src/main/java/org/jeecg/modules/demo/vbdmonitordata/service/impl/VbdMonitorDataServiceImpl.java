package org.jeecg.modules.demo.vbdmonitordata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorData;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;
import org.jeecg.modules.demo.vbdmonitordata.mapper.VbdMonitorDataMapper;
import org.jeecg.modules.demo.vbdmonitordata.mapper.VbdMonitorDetailMapper;
import org.jeecg.modules.demo.vbdmonitordata.service.IVbdMonitorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class VbdMonitorDataServiceImpl extends ServiceImpl<VbdMonitorDataMapper, VbdMonitorData> implements IVbdMonitorDataService {

    @Autowired
    private VbdMonitorDataMapper vbdMonitorDataMapper;
    @Autowired
    private VbdMonitorDetailMapper vbdMonitorDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(VbdMonitorData vbdMonitorData, List<VbdMonitorDetail> vbdMonitorDetailList) {
        vbdMonitorDataMapper.insert(vbdMonitorData);
        if(vbdMonitorDetailList != null && !vbdMonitorDetailList.isEmpty()) {
            for(VbdMonitorDetail entity : vbdMonitorDetailList) {
                entity.setMainId(vbdMonitorData.getId());
                vbdMonitorDetailMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(VbdMonitorData vbdMonitorData, List<VbdMonitorDetail> vbdMonitorDetailList) {
        vbdMonitorDataMapper.updateById(vbdMonitorData);
        // 先删后插策略
        vbdMonitorDetailMapper.deleteByMainId(vbdMonitorData.getId());
        if(vbdMonitorDetailList != null && !vbdMonitorDetailList.isEmpty()) {
            for(VbdMonitorDetail entity : vbdMonitorDetailList) {
                entity.setMainId(vbdMonitorData.getId());
                vbdMonitorDetailMapper.insert(entity);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        vbdMonitorDetailMapper.deleteByMainId(id);
        vbdMonitorDataMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for(Serializable id : idList) {
            vbdMonitorDetailMapper.deleteByMainId(id.toString());
            vbdMonitorDataMapper.deleteById(id);
        }
    }
}
