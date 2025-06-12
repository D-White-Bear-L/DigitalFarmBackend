package com.whitebear.digitalfarmbackend.service.impl;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whitebear.digitalfarmbackend.mapper.DataAnalysisMapper;
import com.whitebear.digitalfarmbackend.model.entity.SoilSampleFull;
import com.whitebear.digitalfarmbackend.service.DataAnalysisService;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Override
    public Map<String, Object> getSoilTrendAnalysis(Integer baseId, Integer pointId, String startDate, String endDate, String paramName) {
        QueryWrapper<SoilSampleFull> queryWrapper = new QueryWrapper<>();
        if (baseId != null) {
            queryWrapper.eq("base_id", baseId);
        }
        if (pointId != null) {
            queryWrapper.eq("point_id", pointId);
        }
        if (startDate != null && endDate != null) {
            queryWrapper.between("sample_date", startDate, endDate);
        }
        queryWrapper.orderByAsc("sample_date");

        List<SoilSampleFull> samples = dataAnalysisMapper.selectList(queryWrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("dates", samples.stream()
                .map(sample -> sample.getSampleDate().format(DateTimeFormatter.ISO_DATE))
                .collect(Collectors.toList()));
        
        // 根据 paramName 动态获取数据
        List<BigDecimal> values = new ArrayList<>();
        for (SoilSampleFull sample : samples) {
            BigDecimal value = null;
            switch (paramName) {
                case "ph": value = sample.getPh(); break;
                case "organicMatter": value = sample.getOrganicMatter(); break;
                case "waterContent": value = sample.getWaterContent(); break;
                case "availableP": value = sample.getAvailableP(); break;
                case "availableK": value = sample.getAvailableK(); break;
                case "availableN": value = sample.getAvailableN(); break;
                case "conductivity": value = sample.getConductivity(); break;
                case "si": value = sample.getSiValue(); break;
                case "s": value = sample.getSValue(); break;
                case "b": value = sample.getBValue(); break;
                case "mo": value = sample.getMoValue(); break;
                case "cl": value = sample.getClValue(); break;
                case "ca": value = sample.getCaValue(); break;
                case "mg": value = sample.getMgValue(); break;
                case "zn": value = sample.getZnValue(); break;
                case "cu": value = sample.getCuValue(); break;
                case "mn": value = sample.getMnValue(); break;
                case "fe": value = sample.getFeValue(); break;
                default: break;
            }
            values.add(value);
        }
        result.put(paramName, values);

        return result;
    }

    @Override
    public Map<String, Object> getSoilMicroAnalysis(Integer baseId, Integer pointId) {
        System.out.println("进入 getSoilMicroAnalysis 方法，baseId: " + baseId + ", pointId: " + pointId);
        QueryWrapper<SoilSampleFull> queryWrapper = new QueryWrapper<>();
        if (baseId != null) {
            queryWrapper.eq("base_id", baseId);
        }
        if (pointId != null) {
            queryWrapper.eq("point_id", pointId);
        }
        queryWrapper.orderByDesc("sample_date").last("LIMIT 1");

        SoilSampleFull latestSample = dataAnalysisMapper.selectOne(queryWrapper);
        System.out.println("查询到的最新土壤样本: " + (latestSample != null ? latestSample.getSampleId() : "无"));
        
        Map<String, Object> result = new HashMap<>();
        if (latestSample != null) {
            result.put("si", latestSample.getSiValue());
            result.put("s", latestSample.getSValue());
            result.put("b", latestSample.getBValue());
            result.put("mo", latestSample.getMoValue());
            result.put("cl", latestSample.getClValue());
            result.put("ca", latestSample.getCaValue());
            result.put("mg", latestSample.getMgValue());
            result.put("zn", latestSample.getZnValue());
            result.put("cu", latestSample.getCuValue());
            result.put("mn", latestSample.getMnValue());
            result.put("fe", latestSample.getFeValue());
        }
        
        System.out.println("getSoilMicroAnalysis 返回结果: " + result);
        return result;
    }

    @Override
    public Map<String, Object> getSoilQualityAnalysis(Integer baseId) {
        QueryWrapper<SoilSampleFull> queryWrapper = new QueryWrapper<>();
        if (baseId != null) {
            queryWrapper.eq("base_id", baseId);
        }
        queryWrapper.orderByDesc("sample_date").last("LIMIT 1");

        SoilSampleFull latestSample = dataAnalysisMapper.selectOne(queryWrapper);
        
        Map<String, Object> result = new HashMap<>();
        if (latestSample != null) {
            // 计算土壤质量评分
            double originalQualityScore = calculateSoilQualityScore(latestSample);
            double percentageQualityScore = Math.min(100.0, (originalQualityScore / 90.0) * 100.0);

            result.put("originalQualityScore", originalQualityScore);
            result.put("qualityScore", percentageQualityScore); // qualityScore now represents percentage
            result.put("qualityLevel", getQualityLevel(originalQualityScore)); // Level based on original score
            result.put("recommendations", generateRecommendations(latestSample));
        }
        
        return result;
    }

    private double calculateSoilQualityScore(SoilSampleFull sample) {
        // 这里实现土壤质量评分算法
        // 使用 BigDecimal 进行精确计算
        BigDecimal score = BigDecimal.ZERO;
        
        // 使用 multiply 方法进行乘法运算，使用 BigDecimal.valueOf 转换权重
        if (sample.getPh() != null) {
            score = score.add(sample.getPh().multiply(BigDecimal.valueOf(0.2)));
        }
        if (sample.getOrganicMatter() != null) {
            score = score.add(sample.getOrganicMatter().multiply(BigDecimal.valueOf(0.2)));
        }
        if (sample.getWaterContent() != null) {
            score = score.add(sample.getWaterContent().multiply(BigDecimal.valueOf(0.15)));
        }
        if (sample.getAvailableN() != null) {
            score = score.add(sample.getAvailableN().multiply(BigDecimal.valueOf(0.15)));
        }
        if (sample.getAvailableP() != null) {
            score = score.add(sample.getAvailableP().multiply(BigDecimal.valueOf(0.15)));
        }
        if (sample.getAvailableK() != null) {
            score = score.add(sample.getAvailableK().multiply(BigDecimal.valueOf(0.15)));
        }
        
        // 将 BigDecimal 转换为 double
        return score.doubleValue();
    }

    private String getQualityLevel(double score) {
        if (score >= 90) return "优";
        if (score >= 80) return "良";
        if (score >= 70) return "中";
        if (score >= 60) return "及格";
        return "差";
    }

    private List<String> generateRecommendations(SoilSampleFull sample) {
        List<String> recommendations = new ArrayList<>();
        
        // pH值建议
        if (sample.getPh() != null) {
            if (sample.getPh().compareTo(BigDecimal.valueOf(6.0)) < 0) {
                recommendations.add("土壤偏酸，建议适当施用石灰调节pH值");
            } else if (sample.getPh().compareTo(BigDecimal.valueOf(8.0)) > 0) {
                recommendations.add("土壤偏碱，建议适当施用酸性肥料调节pH值");
            }
        }
        
        // 有机质建议
        if (sample.getOrganicMatter() != null && 
            sample.getOrganicMatter().compareTo(BigDecimal.valueOf(20)) < 0) {
            recommendations.add("土壤有机质含量偏低，建议增施有机肥");
        }
        
        // 养分建议
        if (sample.getAvailableN() != null && 
            sample.getAvailableN().compareTo(BigDecimal.valueOf(100)) < 0) {
            recommendations.add("土壤氮素含量偏低，建议增施氮肥");
        }
        if (sample.getAvailableP() != null && 
            sample.getAvailableP().compareTo(BigDecimal.valueOf(20)) < 0) {
            recommendations.add("土壤磷素含量偏低，建议增施磷肥");
        }
        if (sample.getAvailableK() != null && 
            sample.getAvailableK().compareTo(BigDecimal.valueOf(100)) < 0) {
            recommendations.add("土壤钾素含量偏低，建议增施钾肥");
        }
        
        return recommendations;
    }
} 