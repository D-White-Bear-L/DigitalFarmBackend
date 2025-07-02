package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private Long userId;
    private String newPassword;
    private Long adminId;
} 