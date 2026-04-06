package org.jeecg.modules.demo.order.vo;

import java.util.List;
import org.jeecg.modules.demo.order.entity.TestOrderMain;
import org.jeecg.modules.demo.order.entity.TestOrderProduct;
import org.jeecg.modules.demo.order.entity.TestOrderCustomer;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description: 测试订单主表
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
@Data
@Schema(description="测试订单主表")
public class TestOrderMainPage {

	/**主键*/
	@Schema(description = "主键")
    private java.lang.String id;
	/**创建人*/
	@Schema(description = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@Schema(description = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**订单编码*/
	@Excel(name = "订单编码", width = 15)
	@Schema(description = "订单编码")
    private java.lang.String orderCode;
	/**下单时间*/
	@Excel(name = "下单时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@Schema(description = "下单时间")
    private java.util.Date orderDate;
	/**描述*/
	@Excel(name = "描述", width = 15)
	@Schema(description = "描述")
    private java.lang.String descc;
	/**下拉多选*/
	@Excel(name = "下拉多选", width = 15, dicCode = "sex")
    @Dict(dicCode = "sex")
	@Schema(description = "下拉多选")
    private java.lang.String xiala;

	@ExcelCollection(name="订单产品明细")
	@Schema(description = "订单产品明细")
	private List<TestOrderProduct> testOrderProductList;
	@ExcelCollection(name="订单客户")
	@Schema(description = "订单客户")
	private List<TestOrderCustomer> testOrderCustomerList;

}
