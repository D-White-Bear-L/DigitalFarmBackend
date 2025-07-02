package com.whitebear.digitalfarmbackend.service;

import java.util.List;

import com.whitebear.digitalfarmbackend.model.dto.RoleRequestDTO;
import com.whitebear.digitalfarmbackend.model.dto.RoleRequestProcessDTO;
import com.whitebear.digitalfarmbackend.model.entity.RoleChangeRequest;
import com.whitebear.digitalfarmbackend.model.entity.User;

public interface RoleManagementService {
    /**
     * 提交角色变更请求
     */
    RoleChangeRequest submitRoleChangeRequest(RoleRequestDTO requestDTO);
    
    /**
     * 获取待处理的角色变更请求
     */
    List<RoleChangeRequest> getPendingRequests();
    
    /**
     * 处理角色变更请求
     */
    boolean processRoleChangeRequest(RoleRequestProcessDTO processDTO);
    
    /**
     * 直接修改用户角色（仅限ADMIN）
     */
    boolean changeUserRole(Long userId, String newRole, Long adminId);
    
    /**
     * 重置用户密码（仅限ADMIN）
     */
    boolean resetUserPassword(Long userId, String newPassword, Long adminId);
}