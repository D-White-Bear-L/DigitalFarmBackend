package com.whitebear.digitalfarmbackend.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("user") // Assuming your table name is 'user'
public class User {
    @TableId(type = IdType.AUTO)
    @TableField("user_id")
    private Long userId;
    private String username;
    private String password;
    @TableField("real_name")
    private String realName;
    private String role; // e.g., admin, manager, user
    @TableField("avatar_url")
    private String avatarUrl;
    private String gender;
    private LocalDateTime birthday;
    private String department;
    private String position;
    private String phone;
    private String email;
    private String address;
    private String bio;
    @TableField("last_login")
    private LocalDateTime lastLogin;
    @TableField("login_ip")
    private String loginIp;
    private String status; // e.g., active, inactive
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("password_updated_at")
    private LocalDateTime passwordUpdatedAt;
} 