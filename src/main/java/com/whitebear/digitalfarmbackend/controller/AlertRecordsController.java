package com.whitebear.digitalfarmbackend.controller;

import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.entity.AlertRecords;
import com.whitebear.digitalfarmbackend.service.AlertRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alert-records")
public class AlertRecordsController {
    @Autowired
    private AlertRecordsService alertRecordsService;

    @GetMapping("/list")
    public Map<String, Object> getAlertRecords(
            @RequestParam(required = false) Integer baseId,
            @RequestParam(required = false) String pointName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        PageResult<AlertRecords> result = alertRecordsService.getAlertRecords(baseId, pointName, startDate, endDate, page, size);
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "获取预警记录成功");
        response.put("data", result);
        
        return response;
    }

    @PutMapping("/{alertId}/status")
    public Map<String, Object> updateAlertStatus(@PathVariable Integer alertId) {
        boolean success = alertRecordsService.updateAlertStatus(alertId);
        
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("code", 200);
            response.put("message", "更新预警状态成功");
        } else {
            response.put("code", 500);
            response.put("message", "更新预警状态失败");
        }
        
        return response;
    }

    @GetMapping("/base-options")
    public Map<String, Object> getBaseOptions() {
        List<Map<String, Object>> options = alertRecordsService.getBaseOptions();
        
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "获取基地选项成功");
        response.put("data", options);
        
        return response;
    }
}
