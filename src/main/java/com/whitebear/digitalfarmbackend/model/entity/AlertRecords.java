package com.whitebear.digitalfarmbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("AlertRecords")
public class AlertRecords {
    @TableId(value = "alert_id", type = IdType.AUTO)
    private Integer alertId;
    private Integer pointId;
    private String alertContent;
    private String alertTime;
    private String alertLevel;
    private Integer isRead; // 0: 未读 1: 已读
    private String readTime;
    
    // 非数据库字段，用于显示
    private String pointName;
    private String baseName;
}
