package org.jeecg.modules.demo.order.service;

import org.jeecg.modules.demo.order.entity.TestOrderProduct;
import org.jeecg.modules.demo.order.entity.TestOrderCustomer;
import org.jeecg.modules.demo.order.entity.TestOrderMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 测试订单主表
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
public interface ITestOrderMainService extends IService<TestOrderMain> {

	/**
	 * 添加一对多
	 *
	 * @param testOrderMain
	 * @param testOrderProductList
	 * @param testOrderCustomerList
	 */
	public void saveMain(TestOrderMain testOrderMain,List<TestOrderProduct> testOrderProductList,List<TestOrderCustomer> testOrderCustomerList) ;
	
	/**
	 * 修改一对多
	 *
   * @param testOrderMain
   * @param testOrderProductList
   * @param testOrderCustomerList
	 */
	public void updateMain(TestOrderMain testOrderMain,List<TestOrderProduct> testOrderProductList,List<TestOrderCustomer> testOrderCustomerList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
