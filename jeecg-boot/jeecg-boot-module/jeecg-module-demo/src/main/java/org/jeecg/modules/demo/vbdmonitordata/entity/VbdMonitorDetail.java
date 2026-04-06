package org.jeecg.modules.demo.vbdmonitordata.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Description: 试验数据记录明细表
 * @Author: Custom Generator
 * @Date:   2026-04-05
 * @Version: V1.0
 */
@Schema(description = "试验数据记录明细表")
@Data
@TableName("vbd_monitor_detail")
public class VbdMonitorDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;

    @Schema(description = "主表ID")
    private String mainId;

    @TableLogic
    @Schema(description = "删除标识")
    private String delFlag;

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

    // --- 业务字段 ---

    @Excel(name = "观测时间", width = 15)
    @Schema(description = "观测时间(如: 1h, 2h)")
    private String observeTime;

    @Excel(name = "组类型", width = 15, replace = {"试验组_1", "空白对照组_2"})
    @Schema(description = "组类型(1:试验组 2:空白对照组)")
    private String groupType;

    @Excel(name = "重复次数", width = 15)
    @Schema(description = "重复次数(1,2,3)")
    private Integer repeatNum;

    @Excel(name = "动物总数", width = 15)
    @Schema(description = "动物总数")
    private Integer animalTotal;

    @Excel(name = "死亡数", width = 15)
    @Schema(description = "死亡数")
    private Integer deadNum;
}