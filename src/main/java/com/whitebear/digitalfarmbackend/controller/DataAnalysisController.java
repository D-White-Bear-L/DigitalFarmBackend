package com.whitebear.digitalfarmbackend.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String paramName) {
        return dataAnalysisService.getSoilTrendAnalysis(baseId, pointId, startDate, endDate, paramName);
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

    // 导出数据
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(
            @RequestParam(required = false) Integer baseId,
            @RequestParam(required = false) Integer pointId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String paramName) {
        try {
            // 获取数据
            Map<String, Object> data;
            if ("micro".equals(type)) {
                data = dataAnalysisService.getSoilMicroAnalysis(baseId, pointId);
            } else {
                data = dataAnalysisService.getSoilTrendAnalysis(baseId, pointId, startDate, endDate, type);
            }

            // 生成CSV内容
            StringBuilder csvContent = new StringBuilder();
            
            // 添加表头
            if ("micro".equals(type)) {
                csvContent.append("元素,含量(mg/kg),状态\n");
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    csvContent.append(entry.getKey())
                            .append(",")
                            .append(entry.getValue())
                            .append(",")
                            .append(getStatusByValue(entry.getKey(), (Double)entry.getValue()))
                            .append("\n");
                }
            } else {
                csvContent.append("日期,数值,状态\n");
                List<String> dates = (List<String>) data.get("dates");
                List<Double> values = (List<Double>) data.get(type);
                
                if (dates != null && values != null) {
                    for (int i = 0; i < dates.size(); i++) {
                        csvContent.append(dates.get(i))
                                .append(",")
                                .append(values.get(i))
                                .append(",")
                                .append(getStatusByValue(type, values.get(i)))
                                .append("\n");
                    }
                }
            }

            // 设置文件名
            String filename = "soil_analysis_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";
            
            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv; charset=UTF-8"));
            headers.setContentDispositionFormData("attachment", filename);
            
            // 返回CSV文件
            System.out.println(csvContent.toString());
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvContent.toString().getBytes(StandardCharsets.UTF_8));
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(("导出失败: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
        }
    }

    // 辅助方法：根据值获取状态
    private String getStatusByValue(String key, Double value) {
        if (value == null) return "未知";
        
        // 阈值定义
        Map<String, Map<String, Double>> thresholds = new HashMap<>();
        thresholds.put("ph", Map.of("low", 6.0, "high", 8.0));
        thresholds.put("waterContent", Map.of("low", 20.0, "high", 40.0));
        thresholds.put("organicMatter", Map.of("low", 20.0, "high", 40.0));
        thresholds.put("conductivity", Map.of("low", 0.1, "high", 0.3));
        thresholds.put("availableN", Map.of("low", 100.0, "high", 300.0));
        thresholds.put("availableP", Map.of("low", 20.0, "high", 60.0));
        thresholds.put("availableK", Map.of("low", 100.0, "high", 300.0));
        thresholds.put("cu", Map.of("low", 0.2, "high", 1.0));
        thresholds.put("zn", Map.of("low", 0.5, "high", 2.0));
        thresholds.put("fe", Map.of("low", 1.0, "high", 3.0));
        thresholds.put("mn", Map.of("low", 0.2, "high", 0.8));
        thresholds.put("b", Map.of("low", 0.1, "high", 0.5));
        thresholds.put("mo", Map.of("low", 0.05, "high", 0.2));
        thresholds.put("cl", Map.of("low", 0.05, "high", 0.2));
        thresholds.put("si", Map.of("low", 5.0, "high", 15.0));
        thresholds.put("mg", Map.of("low", 1.0, "high", 3.0));
        thresholds.put("ca", Map.of("low", 3.0, "high", 8.0));
        thresholds.put("s", Map.of("low", 1.0, "high", 3.0));
        
        Map<String, Double> threshold = thresholds.get(key);
        if (threshold == null) return "正常";
        
        if (value < threshold.get("low")) return "偏低";
        if (value > threshold.get("high")) return "偏高";
        return "正常";
    }
}
