//package com.whitebear.digitalfarmbackend.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.whitebear.digitalfarmbackend.annotation.RequireRole;
//import com.whitebear.digitalfarmbackend.model.entity.User;
//import com.whitebear.digitalfarmbackend.service.jwt.JwtUtil;
//import com.whitebear.digitalfarmbackend.mapper.UserMapper;
//
//@Component
//public class RoleAuthorizationInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 如果不是映射到方法，直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        // 获取方法上的注解
//        RequireRole requireRole = handlerMethod.getMethodAnnotation(RequireRole.class);
//
//        // 如果没有@RequireRole注解，直接通过
//        if (requireRole == null) {
//            return true;
//        }
//
//        // 从请求头中获取token
//        String token = request.getHeader("Authorization");
//        if (token == null || token.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("未授权：缺少token");
//            return false;
//        }
//
//        // 验证token并获取用户名
//        String username;
//        try {
//            username = JwtUtil.getUsernameFromToken(token);
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("未授权：无效的token");
//            return false;
//        }
//
//        // 查询用户信息
//        User user = userMapper.findByUsername(username);
//        if (user == null) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("未授权：用户不存在");
//            return false;
//        }
//
//        // 检查用户角色是否满足要求
//        String[] allowedRoles = requireRole.value();
//        boolean hasPermission = false;
//        for (String role : allowedRoles) {
//            if (role.equals(user.getRole())) {
//                hasPermission = true;
//                break;
//            }
//        }
//
//        if (!hasPermission) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().write("权限不足：需要 " + String.join(" 或 ", allowedRoles) + " 角色");
//            return false;
//        }
//
//        // 将用户信息存入request，方便后续使用
//        request.setAttribute("currentUser", user);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // 不需要实现
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // 不需要实现
//    }
//}