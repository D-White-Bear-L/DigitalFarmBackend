package com.whitebear.digitalfarmbackend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whitebear.digitalfarmbackend.model.entity.MonitoringPoint;

@Mapper
public interface MonitoringPointMapper {

    // 查询所有监测点
    @Select("SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url, " +
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name " +
            "FROM MonitoringPoint mp " +
            "LEFT JOIN Base b ON mp.base_id = b.base_id")
    List<MonitoringPoint> selectAllMonitoringPoints();

    // 参数结合分页查询
    @Select({
            "<script>",
            "SELECT mp.point_id, mp.base_id, mp.point_name, mp.location, mp.image_url,",
            "mp.longitude, mp.latitude, mp.create_time, mp.update_time, b.base_name",
            "FROM MonitoringPoint mp",
            "LEFT JOIN Base b ON mp.base_id = b.base_id",
            "<where>",
            "  <if test='baseId != null'> AND b.base_id = #{baseId} </if>",// 如果baseId不为空，则添加该条件
            "  <if test='keyword != null'> AND mp.point_name LIKE CONCAT('%', #{keyword}, '%') </if>", // 如果keyword不为空，则添加该条件
            "</where>",
            "LIMIT #{offset}, #{pageSize}", // 分页
            "</script>"
    })
    List<MonitoringPoint> selectByConditions(
            // 四个参数都可以为空，根据四个参数的组合进行查询
            @Param("baseId") String baseId,
            @Param("keyword") String keyword,
            @Param("offset") int offset, //  偏移量
            @Param("pageSize") int pageSize // 每页记录数
    );

    @Select({
            "<script>",
            "SELECT COUNT(*) FROM MonitoringPoint mp",
            "LEFT JOIN Base b ON mp.base_id = b.base_id",
            "<where>",
            "  <if test='baseId != null'> AND b.base_id = #{baseId} </if>",
            "  <if test='keyword != null'> AND mp.point_name LIKE CONCAT('%', #{keyword}, '%') </if>",
            "</where>",
            "</script>"
    })
    long countByConditions( // 返回总记录数
            @Param("baseId") String baseId, // 基地ID
            @Param("keyword") String keyword // 关键字
    );


    // 添加监测点
    @Insert("INSERT INTO MonitoringPoint(base_id, point_name, location, image_url, longitude, latitude, create_time, update_time) " +
            "VALUES(#{baseId}, #{pointName}, #{location}, #{imageUrl}, #{longitude}, #{latitude}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "pointId")
    int insertMonitoringPoint(MonitoringPoint monitoringPoint);

    // 更新监测点
    @Update("UPDATE MonitoringPoint SET base_id = #{baseId}, point_name = #{pointName}, " +
            "location = #{location}, image_url = #{imageUrl}, longitude = #{longitude}, " +
            "latitude = #{latitude}, update_time = #{updateTime} " +
            "WHERE point_id = #{pointId}")
    int updateMonitoringPoint(MonitoringPoint monitoringPoint);

    // 删除监测点
    @Delete("DELETE FROM MonitoringPoint WHERE point_id = #{pointId}")
    int deleteMonitoringPoint(Integer pointId);


}