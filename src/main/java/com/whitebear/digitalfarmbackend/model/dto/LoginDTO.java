package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LoginDTO {
    private String username;
    private String password;
    private LocalDateTime lastLogin;
}
