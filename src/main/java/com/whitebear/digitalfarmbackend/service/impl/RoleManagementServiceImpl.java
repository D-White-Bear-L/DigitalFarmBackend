//package com.whitebear.digitalfarmbackend.service.impl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.whitebear.digitalfarmbackend.mapper.RoleChangeRequestMapper;
//import com.whitebear.digitalfarmbackend.mapper.UserMapper;
//import com.whitebear.digitalfarmbackend.model.dto.RoleRequestDTO;
//import com.whitebear.digitalfarmbackend.model.dto.RoleRequestProcessDTO;
//import com.whitebear.digitalfarmbackend.model.entity.RoleChangeRequest;
//import com.whitebear.digitalfarmbackend.model.entity.User;
//import com.whitebear.digitalfarmbackend.model.enums.UserRole;
//import com.whitebear.digitalfarmbackend.service.RoleManagementService;
//
//@Service
//public class RoleManagementServiceImpl implements RoleManagementService {
//
//    @Autowired
//    private RoleChangeRequestMapper roleChangeRequestMapper;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public RoleChangeRequest submitRoleChangeRequest(RoleRequestDTO requestDTO) {
//        User user = userMapper.selectById(requestDTO.getUserId());
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//
//        // 检查是否已有待处理的请求
//        QueryWrapper<RoleChangeRequest> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", requestDTO.getUserId())
//                   .eq("status", "PENDING");
//        Long count = roleChangeRequestMapper.selectCount(queryWrapper);
//        if (count > 0) {
//            throw new RuntimeException("您已有一个待处理的角色变更请求");
//        }
//
//        // 创建新的角色变更请求
//        RoleChangeRequest request = new RoleChangeRequest();
//        request.setUserId(requestDTO.getUserId());
//        request.setCurrentRole(user.getRole());
//        request.setRequestedRole(requestDTO.getRequestedRole());
//        request.setReason(requestDTO.getReason());
//        request.setStatus("PENDING");
//        request.setCreateTime(LocalDateTime.now());
//        request.setUpdateTime(LocalDateTime.now());
//
//        roleChangeRequestMapper.insert(request);
//        return request;
//    }
//
//    @Override
//    public List<RoleChangeRequest> getPendingRequests() {
//        QueryWrapper<RoleChangeRequest> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("status", "PENDING")
//                   .orderByDesc("create_time");
//        List<RoleChangeRequest> requests = roleChangeRequestMapper.selectList(queryWrapper);
//
//        // 填充用户信息
//        for (RoleChangeRequest request : requests) {
//            User user = userMapper.selectById(request.getUserId());
//            request.setUser(user);
//        }
//
//        return requests;
//    }
//
//    @Override
//    @Transactional
//    public boolean processRoleChangeRequest(RoleRequestProcessDTO processDTO) {
//        RoleChangeRequest request = roleChangeRequestMapper.selectById(processDTO.getRequestId());
//        if (request == null) {
//            throw new RuntimeException("请求不存在");
//        }
//
//        // 检查处理人权限
//        User admin = userMapper.selectById(processDTO.getAdminId());
//        if (admin == null || !"ADMIN".equals(admin.getRole())) {
//            throw new RuntimeException("您没有权限处理此请求");
//        }
//
//        // 更新请求状态
//        request.setStatus(processDTO.isApproved() ? "APPROVED" : "REJECTED");
//        request.setAdminId(processDTO.getAdminId());
//        request.setAdminComment(processDTO.getAdminComment());
//        request.setUpdateTime(LocalDateTime.now());
//
//        // 如果批准，更新用户角色
//        if (processDTO.isApproved()) {
//            User user = userMapper.selectById(request.getUserId());
//            if (user != null) {
//                user.setRole(request.getRequestedRole());
//                user.setUpdateTime(LocalDateTime.now());
//                userMapper.updateById(user);
//            }
//        }
//
//        return roleChangeRequestMapper.updateById(request) > 0;
//    }
//
//    @Override
//    @Transactional
//    public boolean changeUserRole(Long userId, String newRole, Long adminId) {
//        // 检查管理员权限
//        User admin = userMapper.selectById(adminId);
//        if (admin == null || !"ADMIN".equals(admin.getRole())) {
//            throw new RuntimeException("您没有权限执行此操作");
//        }
//
//        // 检查角色是否有效
//        try {
//            UserRole.valueOf(newRole);
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("无效的角色");
//        }
//
//        // 更新用户角色
//        User user = userMapper.selectById(userId);
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//
//        user.setRole(newRole);
//        user.setUpdateTime(LocalDateTime.now());
//        return userMapper.updateById(user) > 0;
//    }
//
//    @Override
//    public boolean resetUserPassword(Long userId, String newPassword, Long adminId) {
//        // 检查管理员权限
//        User admin = userMapper.selectById(adminId);
//        if (admin == null || !"ADMIN".equals(admin.getRole())) {
//            throw new RuntimeException("您没有权限执行此操作");
//        }
//
//        // 重置用户密码
//        User user = userMapper.selectById(userId);
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//
//        user.setPassword(passwordEncoder.encode(newPassword));
//        user.setPasswordUpdatedAt(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
//        return userMapper.updateById(user) > 0;
//    }
//}