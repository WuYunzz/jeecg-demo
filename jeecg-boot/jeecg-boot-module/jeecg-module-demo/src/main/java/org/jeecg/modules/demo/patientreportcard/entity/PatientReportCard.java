package org.jeecg.modules.demo.patientreportcard.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 患者报告卡
 * @Author: jeecg-boot
 * @Date:   2026-05-12
 * @Version: V1.0
 */
@Data
@TableName("patient_report_card")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="患者报告卡")
public class PatientReportCard implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;
	/**患者姓名*/
	@Excel(name = "患者姓名", width = 15)
    @Schema(description = "患者姓名")
    private String patientName;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @Schema(description = "身份证号")
    private String idCard;

	/**性别*/
	@Excel(name = "性别", width = 15)
    @Schema(description = "性别")
    @Dict(dicCode = "gender")
    private String patientGender;
	/**患者生日*/
	@Excel(name = "患者生日", width = 15)
    @Schema(description = "患者生日")
    private String patientBirthday;
	/**现住址*/
	@Excel(name = "现住址", width = 15)
    @Schema(description = "现住址")
    private String presentAddress;
	/**发病日期*/
	@Excel(name = "发病日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "发病日期")
    private Date onsetDate;
	/**发病年龄*/
	@Excel(name = "发病年龄", width = 15)
    @Schema(description = "发病年龄")
    private String onsetAge;
	/**报卡日期*/
	@Excel(name = "报卡日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "报卡日期")
    private Date reportDate;
	/**报卡单位*/
	@Excel(name = "报卡单位", width = 15)
    @Schema(description = "报卡单位")
    private String reportOrg;
	/**创建人*/
    @Schema(description = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private String sysOrgCode;
}
