package org.jeecg.modules.demo.vbdmonitordata.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema; // 新版 Swagger3 注解

/**
 * @Description: 病媒监测-耳剂药效试验主表
 * @Author: Custom Generator
 * @Date:   2026-04-05
 * @Version: V1.0
 */
@Schema(description = "病媒监测-耳剂药效试验主表")
@Data
@TableName("vbd_monitor_data")
public class VbdMonitorData implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;

    @TableLogic
    @Schema(description = "删除标识")
    private String delFlag;

    @Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
    @Schema(description = "添加人")
    private String createBy;

    @Excel(name = "添加地区", width = 15, dictTable = "zonecode", dicText = "zonename", dicCode = "zonecode")
    @Dict(dicCode = "zonecode", dicText = "zonename", dictTable = "zonecode")
    @Schema(description = "添加地区")
    private String createZone;

    @Excel(name = "添加机构", width = 15, dictTable = "organise", dicText = "orgname", dicCode = "orgcode")
    @Dict(dicCode = "orgcode", dicText = "orgname", dictTable = "organise")
    @Schema(description = "添加机构")
    private String createOrg;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "添加时间")
    private Date createTime;

    @Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
    @Schema(description = "修改人")
    private String updateBy;

    @Dict(dicCode = "zonecode", dicText = "zonename", dictTable = "zonecode")
    @Schema(description = "修改地区")
    private String updateZone;

    @Dict(dicCode = "orgcode", dicText = "orgname", dictTable = "organise")
    @Schema(description = "修改机构")
    private String updateOrg;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "备注")
    private String remark;

    // --- 业务字段 ---

    @Excel(name = "样品编号", width = 15)
    @Schema(description = "样品编号")
    private String sampleNo;

    @Excel(name = "样品名称", width = 15)
    @Schema(description = "样品名称")
    private String sampleName;

    @Excel(name = "药品品牌及成分", width = 20)
    @Schema(description = "药品品牌及成分")
    private String drugBrandAndComposition;

    @Excel(name = "试验名称", width = 15)
    @Schema(description = "试验名称")
    private String experimentName;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Excel(name = "试验日期", width = 15, format = "yyyy-MM-dd")
    @Schema(description = "试验日期")
    private Date experimentDate;

    @Excel(name = "试验动物", width = 15)
    @Dict(dicCode = "vbd_monitor_data_test_animal")
    @Schema(description = "试验动物")
    private String testAnimal;

    @Excel(name = "试验动物来源", width = 15)
    @Schema(description = "试验动物来源")
    private String testAnimalSource;

    @Excel(name = "试验装置", width = 15)
    @Schema(description = "试验装置")
    private String testDevice;

    @Excel(name = "室内试验温度(℃)", width = 15)
    @Schema(description = "室内试验温度(℃)")
    private BigDecimal indoorTemp;

    @Excel(name = "室内试验湿度(RH%)", width = 15)
    @Schema(description = "室内试验湿度(RH%)")
    private BigDecimal indoorHumidity;

    @Excel(name = "现场试验温度(℃)", width = 15)
    @Schema(description = "现场试验温度(℃)")
    private BigDecimal outdoorTemp;

    @Excel(name = "现场试验湿度(RH%)", width = 15)
    @Schema(description = "现场试验湿度(RH%)")
    private BigDecimal outdoorHumidity;

    @Excel(name = "现场试验风速(m/s)", width = 15)
    @Schema(description = "现场试验风速(m/s)")
    private BigDecimal outdoorWindSpeed;

    @Schema(description = "试验过程")
    private String experimentProcess;

    @Excel(name = "死亡率(%)", width = 15)
    @Schema(description = "死亡率(%)")
    private BigDecimal mortalityRate;

    @Excel(name = "捕杀率(%)", width = 15)
    @Schema(description = "捕杀率(%)")
    private BigDecimal killRate;

    @Excel(name = "校正死亡率(%)", width = 15)
    @Schema(description = "校正死亡率(%)")
    private BigDecimal correctedMortalityRate;

    @Excel(name = "校正捕杀率(%)", width = 15)
    @Schema(description = "校正捕杀率(%)")
    private BigDecimal correctedKillRate;

    @Excel(name = "试验者", width = 15)
    @Schema(description = "试验者")
    private String tester;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "试验者签名日期")
    private Date testerDate;

    @Excel(name = "复核者", width = 15)
    @Schema(description = "复核者")
    private String reviewer;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Schema(description = "复核日期")
    private Date reviewDate;
}