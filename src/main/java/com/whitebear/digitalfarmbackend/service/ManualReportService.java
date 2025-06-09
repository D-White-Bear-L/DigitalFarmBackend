package com.whitebear.digitalfarmbackend.service;

import java.util.Map;

import com.whitebear.digitalfarmbackend.model.dto.ManualReportDTO;
import com.whitebear.digitalfarmbackend.model.dto.PageResult;

public interface ManualReportService {
    PageResult<ManualReportDTO> getList(Integer page, Integer size, Integer baseId, String pointName, String startDate, String endDate);
    Map<String, Object> addManualReport(ManualReportDTO dto);
    Map<String, Object> updateManualReport(ManualReportDTO dto);
    Map<String, Object> deleteManualReport(Integer sampleId);
}
