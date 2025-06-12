package com.whitebear.digitalfarmbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

import com.whitebear.digitalfarmbackend.model.dto.LoginDTO;
import com.whitebear.digitalfarmbackend.model.dto.RegisterDTO;
import com.whitebear.digitalfarmbackend.model.entity.User;
import com.whitebear.digitalfarmbackend.model.vo.Result;
import com.whitebear.digitalfarmbackend.service.UserService;
import com.whitebear.digitalfarmbackend.util.IpUtil;
import com.whitebear.digitalfarmbackend.service.jwt.JwtUtil;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private IpUtil ipUtil;

    @PostMapping("/register")
    public Result<User> register(@Validated @RequestBody RegisterDTO registerDTO) {
        try {
            User user = userService.register(registerDTO);
            return Result.success("注册成功", user);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Validated @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
            if (user != null) {
                String token = JwtUtil.generateToken(user.getUsername()); // 生成token
                String ip = ipUtil.getClientIp(); //  获取客户端IP
                user.setLoginIp(ip);
                Map<String, Object> result = new HashMap<>();
                result.put("token", token);
                result.put("userInfo", user);
                return Result.success("登录成功", result);
            } else {
                return Result.fail("用户名或密码错误");
            }
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
