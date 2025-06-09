package com.whitebear.digitalfarmbackend.service;

import com.whitebear.digitalfarmbackend.model.dto.RegisterDTO;
import com.whitebear.digitalfarmbackend.model.entity.User;

public interface UserService {
    /**
     * 用户注册
     * @param registerDTO 注册信息
     * @return 注册成功返回用户信息，失败返回null
     */
    User register(RegisterDTO registerDTO);

    /**
     * 用户登录
     * @param account 账号（用户名或手机号）
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回null
     */
    User login(String account, String password);

    /**
     * 检查用户名是否已存在
     * @param username 用户名
     * @return true表示已存在
     */
    boolean isUsernameExists(String username);

    /**
     * 检查邮箱是否已存在
     * @param email 邮箱
     * @return true表示已存在
     */
    boolean isEmailExists(String email);
}