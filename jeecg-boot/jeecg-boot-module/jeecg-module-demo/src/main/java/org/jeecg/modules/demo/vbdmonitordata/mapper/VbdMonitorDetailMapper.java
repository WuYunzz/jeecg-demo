package org.jeecg.modules.demo.vbdmonitordata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;

import java.util.List;

public interface VbdMonitorDetailMapper extends BaseMapper<VbdMonitorDetail> {
    public boolean deleteByMainId(@Param("mainId") String mainId);
    public List<VbdMonitorDetail> selectByMainId(@Param("mainId") String mainId);
}