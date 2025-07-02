package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

@Data
public class RoleRequestProcessDTO {
    private Long requestId;
    private boolean approved;
    private String adminComment;
    private Long adminId;
} 