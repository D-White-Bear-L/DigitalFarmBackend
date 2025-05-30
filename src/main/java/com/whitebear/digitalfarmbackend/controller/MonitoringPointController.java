package com.whitebear.digitalfarmbackend.controller;

import com.whitebear.digitalfarmbackend.model.dto.MPPageResult;
import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;
import com.whitebear.digitalfarmbackend.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //  表示该类是一个控制器
@RequestMapping("/api/monitoring")
public class MonitoringPointController {
    @Autowired
    private MonitoringPointService monitoringPointService;
    @GetMapping("/list")
    public Map<String,Object> getAllMonitoringPoints(
            // 两个参数都为空
            @RequestParam(required = false) //  参数名
            String baseId,
            @RequestParam(required = false)
            String keyword,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        MPPageResult<MonitoringPointDTO> resultData = monitoringPointService.getMonitoringPointsByConditions(baseId, keyword,pageNum,pageSize);
        // 返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "获取检测点列表成功");
        response.put("data", resultData);
        System.out.println(response);
        return response;
    }

    @GetMapping("/base-options")
    public Map<String, Object> getBaseOptions(){
        List<Map<String, Object>> options = monitoringPointService.getBaseOptions();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取基地选项成功");
        result.put("data", options);

        return result;
    }



}
