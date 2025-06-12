package com.whitebear.digitalfarmbackend.service;

import java.util.Map;

public interface DataAnalysisService {
    // 获取土壤基础指标趋势分析
    Map<String, Object> getSoilTrendAnalysis(Integer baseId, Integer pointId, String startDate, String endDate, String paramName);

    // 获取土壤微量元素分析
    Map<String, Object> getSoilMicroAnalysis(Integer baseId, Integer pointId);

    // 获取土壤质量评估
    Map<String, Object> getSoilQualityAnalysis(Integer baseId);
} 