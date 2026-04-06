package org.jeecg.modules.demo.order.service.impl;

import org.jeecg.modules.demo.order.entity.TestOrderProduct;
import org.jeecg.modules.demo.order.mapper.TestOrderProductMapper;
import org.jeecg.modules.demo.order.service.ITestOrderProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单产品明细
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
@Service
public class TestOrderProductServiceImpl extends ServiceImpl<TestOrderProductMapper, TestOrderProduct> implements ITestOrderProductService {
	
	@Autowired
	private TestOrderProductMapper testOrderProductMapper;
	
	@Override
	public List<TestOrderProduct> selectByMainId(String mainId) {
		return testOrderProductMapper.selectByMainId(mainId);
	}
}
