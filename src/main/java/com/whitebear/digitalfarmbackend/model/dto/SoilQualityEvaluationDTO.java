package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

@Data
public class SoilQualityEvaluationDTO {
    private Integer evaluationId;
    private Integer pointId;
    private String pointName;
    private String baseName;
    private String qualityLevel; // 质量等级
    private String evaluationDate;
    private String evaluator;
    private String remarks; // 备注
    private String createTime;
}
