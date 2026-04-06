package org.jeecg.modules.demo.vbdmonitordata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;
import org.jeecg.modules.demo.vbdmonitordata.mapper.VbdMonitorDetailMapper;
import org.jeecg.modules.demo.vbdmonitordata.service.IVbdMonitorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VbdMonitorDetailServiceImpl extends ServiceImpl<VbdMonitorDetailMapper, VbdMonitorDetail> implements IVbdMonitorDetailService {

    @Autowired
    private VbdMonitorDetailMapper vbdMonitorDetailMapper;

    @Override
    public List<VbdMonitorDetail> selectByMainId(String mainId) {
        return vbdMonitorDetailMapper.selectByMainId(mainId);
    }
}
