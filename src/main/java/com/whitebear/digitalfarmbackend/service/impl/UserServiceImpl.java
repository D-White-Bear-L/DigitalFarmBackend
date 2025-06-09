package com.whitebear.digitalfarmbackend.service.impl;

import com.whitebear.digitalfarmbackend.mapper.UserMapper;
import com.whitebear.digitalfarmbackend.model.dto.RegisterDTO;
import com.whitebear.digitalfarmbackend.model.entity.User;
import com.whitebear.digitalfarmbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public User register(RegisterDTO registerDTO) {
        // 检查用户名是否已存在
        if (isUsernameExists(registerDTO.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否已存在
        if (isEmailExists(registerDTO.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 创建新用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword())); // 加密密码
        user.setEmail(registerDTO.getEmail());
        user.setRole("user"); // 默认角色
        user.setStatus("active"); // 默认状态

        // 保存用户
        userMapper.insert(user);
        
        // 清除密码后返回
        user.setPassword(null);
        return user;
    }

    @Override
    public User login(String account, String password) {
        // 1. 先查找用户
        User user = userMapper.findByAccount(account);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 2. 校验通过，清除密码后返回
            user.setPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public boolean isUsernameExists(String username) {
        return userMapper.checkUsernameExists(username) > 0;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userMapper.checkEmailExists(email) > 0;
    }
}
