package com.whitebear.digitalfarmbackend.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SoilAnalysisDTO {
    // 趋势分析数据
    private List<String> dates;
    private List<BigDecimal> ph;
    private List<BigDecimal> organicMatter;
    private List<BigDecimal> waterContent;
    private List<BigDecimal> availableP;
    private List<BigDecimal> availableK;
    private List<BigDecimal> availableN;
    private List<BigDecimal> conductivity;

    // 微量元素数据
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

    // 质量评估数据
    private Double qualityScore;
    private String qualityLevel;
    private List<String> recommendations;
} 