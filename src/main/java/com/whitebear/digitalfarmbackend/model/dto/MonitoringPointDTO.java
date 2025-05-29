package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;


@Data
public class MonitoringPointDTO {
    private Integer pointId; // 监控点id
    private String baseName; // 基地名称
    private String pointName; // 监控点名称
    private String location; // 监控点位置
    private String imgUrl; // 监控点图片
    public String createTime; //  创建时间: yyyy-MM-dd HH:mm:ss 传输直接用字符串

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
