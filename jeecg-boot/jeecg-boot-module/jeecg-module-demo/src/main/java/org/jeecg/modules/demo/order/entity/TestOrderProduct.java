package org.jeecg.modules.demo.order.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 订单产品明细
 * @Author: jeecg-boot
 * @Date:   2026-04-04
 * @Version: V1.0
 */
@Schema(description="订单产品明细")
@Data
@TableName("test_order_product")
public class TestOrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**产品名字*/
	@Excel(name = "产品名字", width = 15)
    @Schema(description = "产品名字")
    private java.lang.String productName;
	/**价格*/
	@Excel(name = "价格", width = 15)
    @Schema(description = "价格")
    private java.lang.Double price;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @Schema(description = "数量")
    private java.lang.Integer num;
	/**产品类型*/
	@Excel(name = "产品类型", width = 15, dicCode = "sex")
    @Schema(description = "产品类型")
    private java.lang.String proType;
	/**订单外键ID*/
    @Schema(description = "订单外键ID")
    private java.lang.String orderFkId;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @Schema(description = "描述")
    private java.lang.String descc;
}
