package com.whitebear.digitalfarmbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data // 自动生成getter和setter方法和toString方法
@TableName("Base")
public class FarmBase {
    @TableId(value = "base_id", type = IdType.AUTO)
    private Integer baseId; // 基地ID,MP会讲驼峰转化为下划线

    private String baseName;

    private String location;

    private BigDecimal area;

    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;

}
