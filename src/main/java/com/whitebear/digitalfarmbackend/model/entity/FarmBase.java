package com.whitebear.digitalfarmbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data // 自动生成getter和setter方法
@TableName("Base")
public class FarmBase {
    @TableId(value = "base_id", type = IdType.AUTO)
    private Integer baseId; // 基地ID,MP会讲驼峰转化为下划线

    private String baseName;

    private String location;

    private BigDecimal area;

    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FarmBase{" +
                "baseId=" + baseId +
                ", baseName='" + baseName + '\'' +
                ", location='" + location + '\'' +
                ", area=" + area +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
