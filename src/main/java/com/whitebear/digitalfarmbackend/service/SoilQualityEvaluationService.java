package com.whitebear.digitalfarmbackend.service;
import com.whitebear.digitalfarmbackend.model.dto.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.SoilQualityEvaluationDTO;

public interface SoilQualityEvaluationService {
    // 获取所有
    PageResult<SoilQualityEvaluationDTO> getAllSoilQualityEvaluation(String baseId, String keyword, int pageNum, int pageSize);
    boolean deleteSoilQualityEvaluation(Integer evaluationId);
    boolean addSoilQualityEvaluation(SoilQualityEvaluationDTO soilqualityevaluationDTO);
    boolean updateSoilQualityEvaluation(SoilQualityEvaluationDTO soilqualityevaluationDTO);
}
