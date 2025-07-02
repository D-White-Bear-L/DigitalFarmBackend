package com.whitebear.digitalfarmbackend.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginHistory {
    private Long id;
    private Long userId;
    private LocalDateTime loginTime;
    private String loginIp;
    private String loginLocation;
    private String loginDevice;
    private String loginStatus; // e.g., success, failed
} 