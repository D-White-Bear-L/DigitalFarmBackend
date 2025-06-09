package com.whitebear.digitalfarmbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@TableName("User")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String realName;
    private String role;
    private String avatarUrl;
    private String gender;
    private LocalDate birthday;
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
}
