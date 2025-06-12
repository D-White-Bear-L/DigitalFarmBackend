package com.whitebear.digitalfarmbackend.service.impl;

import com.whitebear.digitalfarmbackend.mapper.AlertRecordsMapper;
import com.whitebear.digitalfarmbackend.mapper.FarmBaseMapper;
import com.whitebear.digitalfarmbackend.model.vo.PageResult;
import com.whitebear.digitalfarmbackend.model.entity.AlertRecords;
import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import com.whitebear.digitalfarmbackend.service.AlertRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertRecordsServiceImpl implements AlertRecordsService {
    
    @Autowired
    private AlertRecordsMapper alertRecordsMapper;
    
    @Autowired
    private FarmBaseMapper farmBaseMapper;

    @Override
    public PageResult<AlertRecords> getAlertRecords(Integer baseId, String pointName, String startDate, String endDate, int page, int size) {
        // 计算偏移量
        int offset = (page - 1) * size;
        
        // 查询数据
        List<AlertRecords> records = alertRecordsMapper.selectAlertRecords(baseId, pointName, startDate, endDate, offset, size);
        
        // 查询总数
        long total = alertRecordsMapper.countAlertRecords(baseId, pointName, startDate, endDate);
        
        return new PageResult<>(records, total, page, size);
    }

    @Override
    public boolean updateAlertStatus(Integer alertId) {
        return alertRecordsMapper.updateAlertStatus(alertId) > 0;
    }

    @Override
    public List<Map<String, Object>> getBaseOptions() {
        List<FarmBase> bases = farmBaseMapper.selectBaseOptions();
        List<Map<String, Object>> options = new ArrayList<>();
        
        for (FarmBase base : bases) {
            Map<String, Object> option = new HashMap<>();
            option.put("label", base.getBaseName());
            option.put("value", base.getBaseId());
            options.add(option);
        }
        
        return options;
    }
}
