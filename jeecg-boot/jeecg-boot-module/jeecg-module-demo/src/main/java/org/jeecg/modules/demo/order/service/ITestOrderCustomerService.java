package org.jeecg.modules.demo.order.service;

import org.jeecg.modules.demo.order.entity.TestOrderCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
public interface ITestOrderCustomerService extends IService<TestOrderCustomer> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<TestOrderCustomer>
	 */
	public List<TestOrderCustomer> selectByMainId(String mainId);
}
