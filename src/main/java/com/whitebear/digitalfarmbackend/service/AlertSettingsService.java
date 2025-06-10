package com.whitebear.digitalfarmbackend.service;

import java.util.List;

import com.whitebear.digitalfarmbackend.model.entity.AlertSettings;

public interface AlertSettingsService {
    List<AlertSettings> getAllAlertSettings();
    boolean updateAlertSetting(AlertSettings alertSettings);
}
