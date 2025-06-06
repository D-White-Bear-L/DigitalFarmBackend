package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MonitoringPointDTO {
    private Integer baseId;
    private Integer pointId; // 监控点id
    private String baseName; // 基地名称
    private String pointName; // 监控点名称
    private String location; // 监控点位置
    private String imageUrl; // 监控点图片
    private BigDecimal longitude; // 经度
    private BigDecimal latitude; // 纬度
    public String createTime; //  创建时间: yyyy-MM-dd HH:mm:ss 传输直接用字符串
}
