package com.whitebear.digitalfarmbackend.controller;

import com.whitebear.digitalfarmbackend.model.dto.ManualReportDTO;
import com.whitebear.digitalfarmbackend.model.dto.PageResult;
import com.whitebear.digitalfarmbackend.service.ManualReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/manualReport")
public class ManualReportController {
    @Autowired
    private ManualReportService manualReportService;

    @GetMapping("/list")
    public Map<String, Object> getList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer baseId,
            @RequestParam(required = false) String pointName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        PageResult<ManualReportDTO> resultData = manualReportService.getList(page, size, baseId, pointName, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取土壤样品列表成功");
        result.put("data", resultData);
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addManualReport(@RequestBody ManualReportDTO dto) {
        dto.setDataSource("manual"); // 设置数据来源为人工上报
        return manualReportService.addManualReport(dto);
    }

    @PutMapping("/update")
    public Map<String, Object> updateManualReport(@RequestBody ManualReportDTO dto) {
        return manualReportService.updateManualReport(dto);
    }

    @DeleteMapping("/delete/{sampleId}")
    public Map<String, Object> deleteManualReport(@PathVariable Integer sampleId) {
        return manualReportService.deleteManualReport(sampleId);
    }
}
