package com.whitebear.digitalfarmbackend.service;

import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.entity.AlertRecords;

import java.util.List;
import java.util.Map;

public interface AlertRecordsService {
    // 获取预警记录列表
    PageResult<AlertRecords> getAlertRecords(Integer baseId, String pointName, String startDate, String endDate, int page, int size);
    
    // 更新预警记录状态
    boolean updateAlertStatus(Integer alertId);
    
    // 获取基地选项
    List<Map<String, Object>> getBaseOptions();
}
