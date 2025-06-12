package com.whitebear.digitalfarmbackend.service;

import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;

import java.util.List;
import java.util.Map;

public interface MonitoringPointService {
    // 获取所有监控点
    List<MonitoringPointDTO> getAllMonitoringPoints();
    // 根据条件查询监控点
    PageResult<MonitoringPointDTO> getMonitoringPointsByConditions(String baseId, String keyword, int pageNum, int pageSize);
    // 获取基地选项
    List<Map<String, Object>> getBaseOptions();
    // 添加监控点
    boolean addMonitoringPoint(MonitoringPointDTO monitoringPointDTO);
    // 修改监控点
    boolean updateMonitoringPoint(MonitoringPointDTO monitoringPointDTO);
    // 删除监控点
    boolean deleteMonitoringPoint(Integer pointId);
}
