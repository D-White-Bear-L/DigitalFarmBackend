package com.whitebear.digitalfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.AlertRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AlertRecordsMapper extends BaseMapper<AlertRecords> {
    
    @Select({
        "<script>",
        "SELECT a.*, m.point_name, b.base_name",
        "FROM AlertRecord a",
        "LEFT JOIN MonitoringPoint m ON a.point_id = m.point_id",
        "LEFT JOIN Base b ON m.base_id = b.base_id",
        "WHERE 1=1",
        "<if test='baseId != null'>",
        "  AND b.base_id = #{baseId}",
        "</if>",
        "<if test='pointName != null and pointName != \"\"'>",
        "  AND m.point_name LIKE CONCAT('%', #{pointName}, '%')",
        "</if>",
        "<if test='startDate != null'>",
        "  AND a.alert_time >= #{startDate}",
        "</if>",
        "<if test='endDate != null'>",
        "  AND a.alert_time &lt;= #{endDate}",
        "</if>",
        "ORDER BY a.alert_time DESC",
        "LIMIT #{offset}, #{pageSize}",
        "</script>"
    })
    List<AlertRecords> selectAlertRecords(
        @Param("baseId") Integer baseId,
        @Param("pointName") String pointName,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate,
        @Param("offset") int offset,
        @Param("pageSize") int pageSize
    );

    @Select({
        "<script>",
        "SELECT COUNT(*)",
        "FROM AlertRecord a",
        "LEFT JOIN MonitoringPoint m ON a.point_id = m.point_id",
        "LEFT JOIN Base b ON m.base_id = b.base_id",
        "WHERE 1=1",
        "<if test='baseId != null'>",
        "  AND b.base_id = #{baseId}",
        "</if>",
        "<if test='pointName != null and pointName != \"\"'>",
        "  AND m.point_name LIKE CONCAT('%', #{pointName}, '%')",
        "</if>",
        "<if test='startDate != null'>",
        "  AND a.alert_time >= #{startDate}",
        "</if>",
        "<if test='endDate != null'>",
        "  AND a.alert_time &lt;= #{endDate}",
        "</if>",
        "</script>"
    })
    long countAlertRecords(
        @Param("baseId") Integer baseId,
        @Param("pointName") String pointName,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate
    );

    @Update("UPDATE AlertRecord SET is_read = 1, read_time = NOW() WHERE alert_id = #{alertId}")
    int updateAlertStatus(@Param("alertId") Integer alertId);
}
