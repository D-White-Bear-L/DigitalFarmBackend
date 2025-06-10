package com.whitebear.digitalfarmbackend.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AlertSettings {
    private Integer settingId;
    private String indicatorName;
    private String conditionType;
    private BigDecimal threshold;
    private Integer decimalPlaces;
    private BigDecimal step;
    private Boolean isActive;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
