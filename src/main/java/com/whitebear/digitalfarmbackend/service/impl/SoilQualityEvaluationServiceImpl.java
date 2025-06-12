package com.whitebear.digitalfarmbackend.service.impl;

import com.whitebear.digitalfarmbackend.mapper.SoilQualityEvaluationMapper;
import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.SoilQualityEvaluationDTO;
import com.whitebear.digitalfarmbackend.service.SoilQualityEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoilQualityEvaluationServiceImpl implements SoilQualityEvaluationService {

    @Autowired
    private SoilQualityEvaluationMapper soilQualityEvaluationMapper;

    @Override
    public PageResult<SoilQualityEvaluationDTO> getAllSoilQualityEvaluation(String baseId, String keyword, int pageNum, int pageSize) {
        // 参数校验
        if (pageNum <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("分页参数必须大于0");
        }

        // 计算起始位置（LIMIT offset, size）
        int offset = (pageNum - 1) * pageSize;

        // 查询当前页数据
        List<SoilQualityEvaluationDTO> list = soilQualityEvaluationMapper.selectByConditions(baseId, keyword, offset, pageSize);
        List<SoilQualityEvaluationDTO> listDTO = convertToDTO(list);
        long total = soilQualityEvaluationMapper.countByConditions(baseId, keyword);
        return new PageResult<>(listDTO, total, pageNum, pageSize);
    }

    @Override
    public boolean deleteSoilQualityEvaluation(Integer evaluationId) {
        try {
            // 首先检查记录是否存在
            int exists = soilQualityEvaluationMapper.checkEvaluationExists(evaluationId);
            if (exists == 0) {
                System.err.println("Record with evaluationId " + evaluationId + " does not exist");
                return false;
            }

            // 尝试删除记录
            int result = soilQualityEvaluationMapper.deleteSoilQualityEvaluation(evaluationId);
            System.out.println("Delete result for evaluationId " + evaluationId + ": " + result);
            
            if (result == 0) {
                System.err.println("Failed to delete record with evaluationId " + evaluationId + " (record exists but delete failed)");
            }
            
            return result > 0;
        } catch (Exception e) {
            System.err.println("Error deleting soil quality evaluation with ID " + evaluationId + ": " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean addSoilQualityEvaluation(SoilQualityEvaluationDTO soilQualityEvaluationDTO) {
        return soilQualityEvaluationMapper.insertSoilQualityEvaluation(soilQualityEvaluationDTO) > 0;
    }

    @Override
    public boolean updateSoilQualityEvaluation(SoilQualityEvaluationDTO soilQualityEvaluationDTO) {
        return soilQualityEvaluationMapper.updateSoilQualityEvaluation(soilQualityEvaluationDTO) > 0;
    }

    private List<SoilQualityEvaluationDTO> convertToDTO(List<SoilQualityEvaluationDTO> list) {
        // 将查询结果转换为DTO列表
        return list.stream().map(item -> {
            SoilQualityEvaluationDTO dto = new SoilQualityEvaluationDTO();
            dto.setEvaluationId(item.getEvaluationId());
            dto.setPointId(item.getPointId());
            dto.setPointName(item.getPointName()); // 监测点名称
            dto.setQualityLevel(item.getQualityLevel());
            dto.setEvaluationDate(item.getEvaluationDate());
            dto.setEvaluator(item.getEvaluator());
            dto.setRemarks(item.getRemarks()); // 备注
            dto.setCreateTime(item.getCreateTime()); // 创建时间
            dto.setBaseName(item.getBaseName()); // 基地名称
            return dto;
        }).collect(Collectors.toList());
    }
}