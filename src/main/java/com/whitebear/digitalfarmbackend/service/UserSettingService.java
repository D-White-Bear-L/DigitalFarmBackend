package com.whitebear.digitalfarmbackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.whitebear.digitalfarmbackend.model.dto.UserDTO;
import com.whitebear.digitalfarmbackend.model.entity.LoginHistory;

public interface UserSettingService {
    UserDTO getUserInfo(Long userId);
    boolean updateUserInfo(UserDTO userDTO);
    boolean changePassword(Long userId, String oldPassword, String newPassword);
    boolean sendVerificationCode(String phone);
    boolean bindPhone(Long userId, String phone, String code);
    List<LoginHistory> getLoginHistory(Long userId);
    String uploadAvatar(Long userId, MultipartFile file) throws IOException;
}
