package com.whitebear.digitalfarmbackend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.whitebear.digitalfarmbackend.model.dto.UserDTO;
import com.whitebear.digitalfarmbackend.model.entity.LoginHistory;
import com.whitebear.digitalfarmbackend.response.ResponseResult;
import com.whitebear.digitalfarmbackend.service.UserSettingService;

@RestController
@RequestMapping("/api/user")
public class UserSettingController {

    @Autowired
    private UserSettingService userSettingService;

    @GetMapping("/info")
    public ResponseResult<UserDTO> getUserInfo() {
        // In a real application, you would get the logged-in user's ID from security context
        // For now, let's assume a default user or get it from a parameter for testing
        Long userId = 1L; // Placeholder for the logged-in user's ID
        UserDTO user = userSettingService.getUserInfo(userId);
        if (user != null) {
            return ResponseResult.success(user);
        } else {
            return ResponseResult.fail("User not found");
        }
    }

    @PostMapping("/update")
    public ResponseResult<Void> updateUserInfo(@RequestBody UserDTO userDTO) {
        System.out.println("Received UserDTO for update: " + userDTO);
        // In a real application, you would ensure the user can only update their own info
        // For now, assume userDTO.getUserId() is valid or use logged-in user's ID
        boolean success = userSettingService.updateUserInfo(userDTO);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail("Failed to update user info");
        }
    }

    @PostMapping("/changePassword")
    public ResponseResult<Void> changePassword(@RequestBody UserDTO userDTO) {
        boolean success = userSettingService.changePassword(userDTO.getUserId(), userDTO.getOldPassword(), userDTO.getNewPassword());
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail("Failed to change password");
        }
    }

    @PostMapping("/sendVerificationCode")
    public ResponseResult<Void> sendVerificationCode(@RequestBody String phone) {
        // Logic to send verification code
        boolean success = userSettingService.sendVerificationCode(phone);
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail("Failed to send verification code");
        }
    }

    @PostMapping("/bindPhone")
    public ResponseResult<Void> bindPhone(@RequestBody UserDTO userDTO) {
        boolean success = userSettingService.bindPhone(userDTO.getUserId(), userDTO.getPhone(), userDTO.getCode());
        if (success) {
            return ResponseResult.success();
        } else {
            return ResponseResult.fail("Failed to bind phone");
        }
    }

    @GetMapping("/loginHistory")
    public ResponseResult<List<LoginHistory>> getLoginHistory(@RequestParam Long userId) {
        List<LoginHistory> history = userSettingService.getLoginHistory(userId);
        return ResponseResult.success(history);
    }

    @PostMapping("/uploadAvatar")
    public ResponseResult<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            String avatarUrl = userSettingService.uploadAvatar(userId, file);
            return ResponseResult.success(avatarUrl);
        } catch (IOException e) {
            return ResponseResult.fail("Failed to upload avatar: " + e.getMessage());
        }
    }

}
