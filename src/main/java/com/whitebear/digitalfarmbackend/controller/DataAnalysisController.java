package com.whitebear.digitalfarmbackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whitebear.digitalfarmbackend.service.DataAnalysisService;

@RestController
@RequestMapping("/api/dataAnalysis")
public class DataAnalysisController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    // 获取土壤基础指标趋势分析
    @GetMapping("/soil/trend")
    public Map<String, Object> getSoilTrendAnalysis(
            @RequestParam(required = false) Integer baseId,
            @RequestParam(required = false) Integer pointId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return dataAnalysisService.getSoilTrendAnalysis(baseId, pointId, startDate, endDate);
    }

    // 获取土壤微量元素分析
    @GetMapping("/soil/micro")
    public Map<String, Object> getSoilMicroAnalysis(
            @RequestParam(required = false) Integer baseId,
            @RequestParam(required = false) Integer pointId) {
        return dataAnalysisService.getSoilMicroAnalysis(baseId, pointId);
    }

    // 获取土壤质量评估
    @GetMapping("/soil/quality")
    public Map<String, Object> getSoilQualityAnalysis(
            @RequestParam(required = false) Integer baseId) {
        return dataAnalysisService.getSoilQualityAnalysis(baseId);
    }
}
