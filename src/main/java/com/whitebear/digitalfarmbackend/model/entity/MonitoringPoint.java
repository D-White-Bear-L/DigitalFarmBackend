package com.whitebear.digitalfarmbackend.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;


@Data
@TableName("MonitoringPoint")
public class MonitoringPoint {
    @TableId(value = "point_id", type = IdType.AUTO)
    private Integer pointId;
    private Integer baseId;
    private String pointName;
    private String location;
    private String imageUrl;
    private BigDecimal longitude; // 经度
    private BigDecimal latitude; // 纬度
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @TableField(exist = false) // 表示该字段不是数据库中的字段，只用于映射
    private String baseName;

}
