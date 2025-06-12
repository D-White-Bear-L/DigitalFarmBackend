package com.whitebear.digitalfarmbackend.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whitebear.digitalfarmbackend.mapper.UserMapper;
import com.whitebear.digitalfarmbackend.model.dto.UserDTO;
import com.whitebear.digitalfarmbackend.model.entity.LoginHistory;
import com.whitebear.digitalfarmbackend.model.entity.User;
import com.whitebear.digitalfarmbackend.service.UserSettingService;

@Service
public class UserSettingServiceImpl implements UserSettingService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Value("${file.upload.path}")
    private String uploadPath;
    
    @Value("${file.access.path}")
    private String accessPath;

    @Override
    public UserDTO getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
        return null;
    }

    @Override
    public boolean updateUserInfo(UserDTO userDTO) {
        User user = userMapper.selectById(userDTO.getUserId());
        if (user == null) {
            return false;
        }
        BeanUtils.copyProperties(userDTO, user);
        user.setUpdateTime(LocalDateTime.now());
        System.out.println("User object before updateById: " + user);
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            return false;
        }
        
        user.setPassword(newPassword);
        user.setPasswordUpdatedAt(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean sendVerificationCode(String phone) {
        System.out.println("Sending verification code to: " + phone);
        return true;
    }

    @Override
    public boolean bindPhone(Long userId, String phone, String code) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }
        
        user.setPhone(phone);
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(user) > 0;
    }

    @Override
    public String uploadAvatar(Long userId, MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;
        File destFile = new File(uploadPath + File.separator + filename);

        file.transferTo(destFile);

        String avatarUrl = accessPath + filename;
        User user = userMapper.selectById(userId);
        if (user != null) {
            user.setAvatarUrl(avatarUrl);
            user.setUpdateTime(LocalDateTime.now());
            userMapper.updateById(user);
        }
        return avatarUrl;
    }

    @Override
    public List<LoginHistory> getLoginHistory(Long userId) {
        User user = userMapper.selectById(userId);
        List<LoginHistory> history = new ArrayList<>();

        if (user != null) {
            LoginHistory recentLogin = new LoginHistory();
            recentLogin.setId(1L);
            recentLogin.setUserId(userId);
            recentLogin.setLoginTime(user.getLastLogin());
            recentLogin.setLoginIp(user.getLoginIp());
            recentLogin.setLoginLocation("模拟地点");
            recentLogin.setLoginDevice("模拟设备");
            recentLogin.setLoginStatus("success");
            history.add(recentLogin);

            LoginHistory olderLogin1 = new LoginHistory();
            olderLogin1.setId(2L);
            olderLogin1.setUserId(userId);
            olderLogin1.setLoginTime(LocalDateTime.now().minusDays(5));
            olderLogin1.setLoginIp("192.168.1.1");
            olderLogin1.setLoginLocation("模拟地点A");
            olderLogin1.setLoginDevice("Windows PC");
            olderLogin1.setLoginStatus("success");
            history.add(olderLogin1);

            LoginHistory olderLogin2 = new LoginHistory();
            olderLogin2.setId(3L);
            olderLogin2.setUserId(userId);
            olderLogin2.setLoginTime(LocalDateTime.now().minusMonths(1));
            olderLogin2.setLoginIp("10.0.0.5");
            olderLogin2.setLoginLocation("模拟地点B");
            olderLogin2.setLoginDevice("Android Phone");
            olderLogin2.setLoginStatus("failed");
            history.add(olderLogin2);
        }
        return history;
    }
}
