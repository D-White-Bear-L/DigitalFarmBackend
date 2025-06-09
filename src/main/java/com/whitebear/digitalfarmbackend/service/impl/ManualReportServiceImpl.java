package com.whitebear.digitalfarmbackend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whitebear.digitalfarmbackend.mapper.ManualReportMapper;
import com.whitebear.digitalfarmbackend.model.dto.ManualReportDTO;
import com.whitebear.digitalfarmbackend.model.dto.PageResult;
import com.whitebear.digitalfarmbackend.service.ManualReportService;

@Service
public class ManualReportServiceImpl implements ManualReportService {
    @Autowired
    private ManualReportMapper manualReportMapper;

    @Override
    public PageResult<ManualReportDTO> getList(Integer page, Integer size, Integer baseId, String pointName, String startDate, String endDate) {
        QueryWrapper<ManualReportDTO> wrapper = new QueryWrapper<>();
        if (baseId != null) {
            wrapper.eq("base_id", baseId);
        }
        if (pointName != null && !pointName.isEmpty()) {
            wrapper.like("soil_sample_name", pointName);
        }
        if (startDate != null && !startDate.isEmpty()) {
            wrapper.ge("sample_date", startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            wrapper.le("sample_date", endDate);
        }
        wrapper.orderByDesc("sample_date");
        Page<ManualReportDTO> pageObj = new Page<>(page, size);
        IPage<ManualReportDTO> resultPage = manualReportMapper.selectPageWithPointName(pageObj, wrapper);
        Long total = manualReportMapper.countManualReportsWithPointName(wrapper);
        return new PageResult<>(resultPage.getRecords(), total, page, size);
    }

    @Override
    public Map<String, Object> addManualReport(ManualReportDTO dto) {
        Map<String, Object> result = new HashMap<>();
        int insert = manualReportMapper.insert(dto);
        if (insert > 0) {
            result.put("code", 200);
            result.put("message", "添加成功");
        } else {
            result.put("code", 500);
            result.put("message", "添加失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> updateManualReport(ManualReportDTO dto) {
        Map<String, Object> result = new HashMap<>();
        int update = manualReportMapper.updateById(dto);
        if (update > 0) {
            result.put("code", 200);
            result.put("message", "更新成功");
        } else {
            result.put("code", 500);
            result.put("message", "更新失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> deleteManualReport(Integer sampleId) {
        Map<String, Object> result = new HashMap<>();
        int delete = manualReportMapper.deleteById(sampleId);
        if (delete > 0) {
            result.put("code", 200);
            result.put("message", "删除成功");
        } else {
            result.put("code", 404);
            result.put("message", "记录不存在或已被删除");
        }
        return result;
    }
}
