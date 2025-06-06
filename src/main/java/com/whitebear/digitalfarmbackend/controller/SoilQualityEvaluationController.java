package com.whitebear.digitalfarmbackend.controller;

import com.whitebear.digitalfarmbackend.model.dto.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.SoilQualityEvaluationDTO;
import com.whitebear.digitalfarmbackend.service.SoilQualityEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/soilQualityEvaluation")
public class SoilQualityEvaluationController {
    @Autowired
    private SoilQualityEvaluationService soilQualityEvaluationService;
    @RequestMapping("/list")
    public Map<String, Object> getAllSoilQualityEvaluation(
            @RequestParam(required = false) String baseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int pageNum, // 当前页码
            @RequestParam(defaultValue = "10") int pageSize
    ){
        PageResult<SoilQualityEvaluationDTO> resultData = soilQualityEvaluationService.getAllSoilQualityEvaluation(baseId, keyword, pageNum, pageSize);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取土壤质量评价列表成功");
        result.put("data", resultData);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/add")
    public Map<String, Object> addSoilQualityEvaluation(@RequestBody SoilQualityEvaluationDTO soilqualityevaluationDTO){
        boolean success = soilQualityEvaluationService.addSoilQualityEvaluation(soilqualityevaluationDTO);
        System.out.println("add:"+soilqualityevaluationDTO);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("code", 200);
            result.put("message", "添加土壤质量评估数据成功");
        }else {
            result.put("code", 500);
            result.put("message", "添加土壤质量评估数据失败");
        }
        return result;
    }

    @RequestMapping("/update")
    public Map<String, Object> updateSoilQualityEvaluation(@RequestBody SoilQualityEvaluationDTO soilQualityEvaluationDTO){
        boolean success = soilQualityEvaluationService.updateSoilQualityEvaluation(soilQualityEvaluationDTO);
        System.out.println("update:"+soilQualityEvaluationDTO);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("code", 200);
            result.put("message", "更新土壤质量评估数据成功");
        } else {
            result.put("code", 500);
            result.put("message", "更新土壤质量评估数据失败");
            System.out.println("update:"+soilQualityEvaluationDTO);
        }
        return result;
    }

    @RequestMapping("/delete/{evaluationId}")
    public Map<String, Object> deleteSoilQualityEvaluation(@PathVariable Integer evaluationId){
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = soilQualityEvaluationService.deleteSoilQualityEvaluation(evaluationId);
            System.out.println("Delete attempt for evaluationId: " + evaluationId + ", success: " + success);
            
            if (success) {
                result.put("code", 200);
                result.put("message", "删除土壤质量评估数据成功");
            } else {
                result.put("code", 404);
                result.put("message", "删除土壤质量评估数据失败：记录不存在或已被删除");
            }
        } catch (Exception e) {
            System.err.println("Error in delete operation for evaluationId " + evaluationId + ": " + e.getMessage());
            e.printStackTrace();
            result.put("code", 500);
            result.put("message", "删除土壤质量评估数据失败：" + e.getMessage());
        }
        return result;
    }
}
