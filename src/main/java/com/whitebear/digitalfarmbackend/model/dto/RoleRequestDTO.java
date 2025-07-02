package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

@Data
public class RoleRequestDTO {
    private Integer userId;
    private String requestedRole;
    private String reason;
    private Long adminId;
}