//package com.whitebear.digitalfarmbackend.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.whitebear.digitalfarmbackend.model.dto.RoleRequestDTO;
//import com.whitebear.digitalfarmbackend.model.dto.RoleRequestProcessDTO;
//import com.whitebear.digitalfarmbackend.model.dto.ResetPasswordDTO;
//import com.whitebear.digitalfarmbackend.model.entity.RoleChangeRequest;
//import com.whitebear.digitalfarmbackend.model.vo.Result;
//import com.whitebear.digitalfarmbackend.service.RoleManagementService;
//
//@RestController
//@RequestMapping("/api/role")
//public class RoleManagementController {
//
//    @Autowired
//    private RoleManagementService roleManagementService;
//
//    @PostMapping("/request")
//    public Result<RoleChangeRequest> submitRoleChangeRequest(@RequestBody RoleRequestDTO requestDTO) {
//        try {
//            RoleChangeRequest request = roleManagementService.submitRoleChangeRequest(requestDTO);
//            return Result.success("申请提交成功", request);
//        } catch (Exception e) {
//            return Result.fail(e.getMessage());
//        }
//    }
//
//    @GetMapping("/pending-requests")
//    public Result<List<RoleChangeRequest>> getPendingRequests() {
//        try {
//            List<RoleChangeRequest> requests = roleManagementService.getPendingRequests();
//            return Result.success(requests);
//        } catch (Exception e) {
//            return Result.fail(e.getMessage());
//        }
//    }
//
//    @PostMapping("/process-request")
//    public Result<String> processRoleChangeRequest(@RequestBody RoleRequestProcessDTO processDTO) {
//        try {
//            boolean success = roleManagementService.processRoleChangeRequest(processDTO);
//            if (success) {
//                return Result.success("处理成功");
//            } else {
//                return Result.fail("处理失败");
//            }
//        } catch (Exception e) {
//            return Result.fail(e.getMessage());
//        }
//    }
//
//    @PostMapping("/change-role")
//    public Result<String> changeUserRole(@RequestBody RoleRequestDTO requestDTO) {
//        try {
//            boolean success = roleManagementService.changeUserRole(
//                requestDTO.getUserId().longValue(),
//                requestDTO.getRequestedRole(),
//                requestDTO.getAdminId());
//            if (success) {
//                return Result.success("角色修改成功");
//            } else {
//                return Result.fail("角色修改失败");
//            }
//        } catch (Exception e) {
//            return Result.fail(e.getMessage());
//        }
//    }
//
//    @PostMapping("/reset-password")
//    public Result<String> resetUserPassword(@RequestBody ResetPasswordDTO resetDTO) {
//        try {
//            boolean success = roleManagementService.resetUserPassword(
//                resetDTO.getUserId(),
//                resetDTO.getNewPassword(),
//                resetDTO.getAdminId());
//            if (success) {
//                return Result.success("密码重置成功");
//            } else {
//                return Result.fail("密码重置失败");
//            }
//        } catch (Exception e) {
//            return Result.fail(e.getMessage());
//        }
//    }
//}