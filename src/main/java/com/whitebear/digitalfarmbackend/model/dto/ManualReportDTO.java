package com.whitebear.digitalfarmbackend.model.dto;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@TableName("soilsamplefull")
public class ManualReportDTO {
    @TableId
    private Integer sampleId;
    private Integer baseId;
    private Integer pointId;
    private String pointName;
    private String soilSampleNo;
    private String soilSampleName;
    private String soilType;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String sampleDepth;
    private String sampleDate;
    private String reporter;
    private String phone;
    
    // 基础指标
    private BigDecimal ph;
    private BigDecimal organicMatter;
    private BigDecimal waterContent;
    private BigDecimal availableP;
    private BigDecimal availableK;
    private BigDecimal availableN;
    private BigDecimal conductivity;
    
    // 微量元素
    private BigDecimal siValue;
    private BigDecimal sValue;
    private BigDecimal bValue;
    private BigDecimal caValue;
    private BigDecimal cuValue;
    private BigDecimal znValue;
    private BigDecimal mnValue;
    private BigDecimal mgValue;
    
    private String dataSource;
}
