package org.jeecg.modules.demo.order.service.impl;

import org.jeecg.modules.demo.order.entity.TestOrderMain;
import org.jeecg.modules.demo.order.entity.TestOrderProduct;
import org.jeecg.modules.demo.order.entity.TestOrderCustomer;
import org.jeecg.modules.demo.order.mapper.TestOrderProductMapper;
import org.jeecg.modules.demo.order.mapper.TestOrderCustomerMapper;
import org.jeecg.modules.demo.order.mapper.TestOrderMainMapper;
import org.jeecg.modules.demo.order.service.ITestOrderMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 测试订单主表
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
@Service
public class TestOrderMainServiceImpl extends ServiceImpl<TestOrderMainMapper, TestOrderMain> implements ITestOrderMainService {

	@Autowired
	private TestOrderMainMapper testOrderMainMapper;
	@Autowired
	private TestOrderProductMapper testOrderProductMapper;
	@Autowired
	private TestOrderCustomerMapper testOrderCustomerMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(TestOrderMain testOrderMain, List<TestOrderProduct> testOrderProductList,List<TestOrderCustomer> testOrderCustomerList) {
		testOrderMainMapper.insert(testOrderMain);
		if(testOrderProductList!=null && testOrderProductList.size()>0) {
			for(TestOrderProduct entity:testOrderProductList) {
				//外键设置
				entity.setOrderFkId(testOrderMain.getId());
				testOrderProductMapper.insert(entity);
			}
		}
		if(testOrderCustomerList!=null && testOrderCustomerList.size()>0) {
			for(TestOrderCustomer entity:testOrderCustomerList) {
				//外键设置
				entity.setOrderId(testOrderMain.getId());
				testOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(TestOrderMain testOrderMain,List<TestOrderProduct> testOrderProductList,List<TestOrderCustomer> testOrderCustomerList) {
		testOrderMainMapper.updateById(testOrderMain);
		
		//1.先删除子表数据
		testOrderProductMapper.deleteByMainId(testOrderMain.getId());
		testOrderCustomerMapper.deleteByMainId(testOrderMain.getId());
		
		//2.子表数据重新插入
		if(testOrderProductList!=null && testOrderProductList.size()>0) {
			for(TestOrderProduct entity:testOrderProductList) {
				//外键设置
				entity.setOrderFkId(testOrderMain.getId());
				testOrderProductMapper.insert(entity);
			}
		}
		if(testOrderCustomerList!=null && testOrderCustomerList.size()>0) {
			for(TestOrderCustomer entity:testOrderCustomerList) {
				//外键设置
				entity.setOrderId(testOrderMain.getId());
				testOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		testOrderProductMapper.deleteByMainId(id);
		testOrderCustomerMapper.deleteByMainId(id);
		testOrderMainMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			testOrderProductMapper.deleteByMainId(id.toString());
			testOrderCustomerMapper.deleteByMainId(id.toString());
			testOrderMainMapper.deleteById(id);
		}
	}
	
}
