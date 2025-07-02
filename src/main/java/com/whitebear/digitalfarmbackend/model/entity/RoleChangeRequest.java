package com.whitebear.digitalfarmbackend.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("role_change_request")
public class RoleChangeRequest {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Integer userId;
    
    private String currentRole;
    
    private String requestedRole;
    
    private String reason;
    
    private String status; // PENDING, APPROVED, REJECTED
    
    private Long adminId;
    
    private String adminComment;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
    
    @TableField(exist = false)
    private User user;
    
    @TableField(exist = false)
    private User admin;
}