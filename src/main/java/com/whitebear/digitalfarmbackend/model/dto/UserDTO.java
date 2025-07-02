package com.whitebear.digitalfarmbackend.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String realName;
    private String role;
    private String avatarUrl;
    private String gender;
    private LocalDateTime birthday;
    private String department;
    private String position;
    private String phone;
    private String email;
    private String address;
    private String bio;
    private LocalDateTime lastLogin;
    private String loginIp;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime passwordUpdatedAt;
    private String oldPassword;
    private String newPassword;
    private String code; // For phone verification code
} 