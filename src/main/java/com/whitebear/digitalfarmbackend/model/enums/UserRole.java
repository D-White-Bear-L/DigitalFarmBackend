package com.whitebear.digitalfarmbackend.model.enums;

public enum UserRole {
    USER("普通用户"),
    MANAGER("管理员"),
    ADMIN("超级管理员");
    
    private final String description;
    
    UserRole(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}