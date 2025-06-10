package com.whitebear.digitalfarmbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitebear.digitalfarmbackend.model.entity.AlertSettings;
import com.whitebear.digitalfarmbackend.service.AlertSettingsService;

@RestController
@RequestMapping("/api/alertSettings")
public class AlertSettingsController {
    @Autowired
    private AlertSettingsService alertSettingsService;

    @GetMapping
    public ResponseEntity<List<AlertSettings>> getAllAlertSettings() {
        List<AlertSettings> settings = alertSettingsService.getAllAlertSettings();
        return ResponseEntity.ok(settings);
    }

    @PutMapping
    public ResponseEntity<Void> updateAlertSetting(@RequestBody AlertSettings alertSettings) {
        boolean updated = alertSettingsService.updateAlertSetting(alertSettings);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
