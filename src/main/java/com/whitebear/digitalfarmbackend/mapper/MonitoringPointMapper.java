package com.whitebear.digitalfarmbackend.mapper;

import com.whitebear.digitalfarmbackend.model.entity.MonitoringPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MonitoringPointMapper {

    // 查询所有监测点
    @Select("SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url, " +
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name " +
            "FROM MonitoringPoint mp " +
            "LEFT JOIN Base b ON mp.base_id = b.base_id")
    List<MonitoringPoint> selectAllMonitoringPoints();

    // 根据基地ID查询监测点
    @Select("SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url, " +
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name " +
            "FROM MonitoringPoint mp " +
            "LEFT JOIN Base b ON mp.base_id = b.base_id " +
            "WHERE b.base_id = #{baseId}")
    List<MonitoringPoint> selectMonitoringPointsByBaseId(Integer baseId);

    // 根据关键字查询监测点
    @Select("SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url, " +
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name " +
            "FROM MonitoringPoint mp " +
            "LEFT JOIN Base b ON mp.base_id = b.base_id " +
            "WHERE mp.point_name LIKE CONCAT('%', #{keyword}, '%')")
    List<MonitoringPoint> selectMonitoringPointsByKeyword(String keyword);

    // 根据基地ID和关键字查询监测点
    @Select("SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url, " +
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name " +
            "FROM MonitoringPoint mp " +
            "LEFT JOIN Base b ON mp.base_id = b.base_id " +
            "WHERE b.base_id = #{baseId} AND mp.point_name LIKE CONCAT('%', #{keyword}, '%')")
    List<MonitoringPoint> selectMonitoringPointsByBaseIdAndKeyword(Integer baseId, String keyword);
}