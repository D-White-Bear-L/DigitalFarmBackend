package com.whitebear.digitalfarmbackend.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
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

//    @TableField(exist = false) // 表示该字段不是数据库中的字段，只用于映射
    private String baseName;

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public String toString() {
        return "MonitoringPoint{" +
                "pointId=" + pointId +
                ", baseId=" + baseId +
                ", pointName='" + pointName + '\'' +
                ", location='" + location + '\'' +
                ", imgUrl='" + imageUrl + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
