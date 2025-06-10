package com.whitebear.digitalfarmbackend.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("SoilSampleFull")
public class SoilSampleFull {
    @TableId(type = IdType.AUTO)
    private Integer sampleId;
    private Integer baseId;
    private Integer pointId;
    private String soilSampleNo;
    private String soilType;
    private String soilSampleName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String sampleDepth;
    private LocalDate sampleDate;
    private String reporter;
    private String phone;
    private LocalDateTime reportTime;

    // 基础理化指标
    private BigDecimal ph;
    private BigDecimal organicMatter;
    private BigDecimal waterContent;
    private BigDecimal availableP;
    private BigDecimal availableK;
    private BigDecimal availableN;
    private BigDecimal conductivity;

    // 微量元素指标
    private BigDecimal siValue;
    private BigDecimal sValue;
    private BigDecimal bValue;
    private BigDecimal moValue;
    private BigDecimal clValue;
    private BigDecimal caValue;
    private BigDecimal mgValue;
    private BigDecimal znValue;
    private BigDecimal cuValue;
    private BigDecimal mnValue;
    private BigDecimal feValue;

    // 数据来源
    private String dataSource;

    // 系统字段
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 