package com.whitebear.digitalfarmbackend.service;

import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;

import java.util.List;
import java.util.Map;

public interface MonitoringPointService {
    // 获取所有监控点
    List<MonitoringPointDTO> getAllMonitoringPoints();
    // 根据条件查询监控点
    List<MonitoringPointDTO> getMonitoringPointsByConditions(String baseId, String keyword);
    // 获取基地选项
    List<Map<String, Object>> getBaseOptions();
}
