package org.jeecg.modules.demo.vbdmonitordata.vo;

import java.util.List;
import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import org.jeecg.modules.demo.vbdmonitordata.entity.VbdMonitorDetail;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description: 病媒监测-耳剂药效试验主表 VO
 * @Author: Custom Generator
 * @Date:   2026-04-05
 * @Version: V1.0
 */
@Data
@Schema(description="病媒监测-耳剂药效试验主表VO")
public class VbdMonitorDataPage {

    @Schema(description = "主键")
    private String id;

    @Schema(description = "添加人")
    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "添加时间")
    private Date createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private Date updateTime;

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

    @Schema(description = "室内试验温度(℃)")
    private BigDecimal indoorTemp;

    @Schema(description = "室内试验湿度(RH%)")
    private BigDecimal indoorHumidity;

    @Schema(description = "现场试验温度(℃)")
    private BigDecimal outdoorTemp;

    @Schema(description = "现场试验湿度(RH%)")
    private BigDecimal outdoorHumidity;

    @Schema(description = "现场试验风速(m/s)")
    private BigDecimal outdoorWindSpeed;

    @Schema(description = "试验过程")
    private String experimentProcess;

    @Schema(description = "死亡率(%)")
    private BigDecimal mortalityRate;

    @Schema(description = "捕杀率(%)")
    private BigDecimal killRate;

    @Schema(description = "校正死亡率(%)")
    private BigDecimal correctedMortalityRate;

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

    @ExcelCollection(name="试验数据记录明细")
    @Schema(description = "试验数据记录明细")
    private List<VbdMonitorDetail> vbdMonitorDetailList;
}