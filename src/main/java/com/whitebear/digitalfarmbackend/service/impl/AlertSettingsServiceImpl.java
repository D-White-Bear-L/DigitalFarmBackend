package com.whitebear.digitalfarmbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitebear.digitalfarmbackend.mapper.AlertSettingsMapper;
import com.whitebear.digitalfarmbackend.model.entity.AlertSettings;
import com.whitebear.digitalfarmbackend.service.AlertSettingsService;

@Service
public class AlertSettingsServiceImpl implements AlertSettingsService {

    @Autowired
    private AlertSettingsMapper alertSettingsMapper;

    @Override
    public List<AlertSettings> getAllAlertSettings() {
        return alertSettingsMapper.findAllAlertSettings();
    }

    @Override
    public boolean updateAlertSetting(AlertSettings alertSettings) {
        return alertSettingsMapper.updateAlertSetting(alertSettings) > 0;
    }
}
