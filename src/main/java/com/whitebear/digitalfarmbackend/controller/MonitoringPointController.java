package com.whitebear.digitalfarmbackend.controller;

import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.dto.MonitoringPointDTO;
import com.whitebear.digitalfarmbackend.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            // 两个参数都可以为空
            @RequestParam(required = false) String baseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int pageNum, // 当前页码
            @RequestParam(defaultValue = "10") int pageSize
    ){
        PageResult<MonitoringPointDTO> resultData = monitoringPointService.getMonitoringPointsByConditions(baseId, keyword,pageNum,pageSize);
        // 返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "获取检测点列表成功");
        response.put("data", resultData);
        System.out.println("listResponse:"+response);
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

    @PostMapping("/add")
    public Map<String, Object> addMonitoringPoint(@RequestBody MonitoringPointDTO monitoringPointDTO) {
        boolean success = monitoringPointService.addMonitoringPoint(monitoringPointDTO); // 调用业务逻辑层方法
        System.out.println("addMonitoringPoint:"+monitoringPointDTO);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("code", 200);
            result.put("message", "添加监测点成功");
        } else {
            result.put("code", 500);
            result.put("message", "添加监测点失败");
        }

        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updateMonitoringPoint(@RequestBody MonitoringPointDTO monitoringPointDTO) {
        boolean success = monitoringPointService.updateMonitoringPoint(monitoringPointDTO);

         System.out.println("updateMonitoringPoint:"+monitoringPointDTO);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("code", 200);
            result.put("message", "更新监测点成功");
        } else {
            result.put("code", 500);
            result.put("message", "更新监测点失败");
        }

        return result;
    }

    @DeleteMapping("/delete/{pointId}")
    public Map<String, Object> deleteMonitoringPoint(@PathVariable Integer pointId) {
        boolean success = monitoringPointService.deleteMonitoringPoint(pointId);

         System.out.println("deleteMonitoringPoint:"+pointId);
        Map<String, Object> result = new HashMap<>();
        if (success) {
            result.put("code", 200);
            result.put("message", "删除监测点成功");
        } else {
            result.put("code", 500);
            result.put("message", "删除监测点失败");
        }

        return result;
    }



}
