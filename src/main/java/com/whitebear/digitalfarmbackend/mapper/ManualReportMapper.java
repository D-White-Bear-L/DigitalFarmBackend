package com.whitebear.digitalfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whitebear.digitalfarmbackend.model.dto.ManualReportDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ManualReportMapper extends BaseMapper<ManualReportDTO> {

    @Select("SELECT s.*, m.point_name FROM soilsamplefull s LEFT JOIN MonitoringPoint m ON s.point_id = m.point_id ${ew.customSqlSegment}")
    IPage<ManualReportDTO> selectPageWithPointName(IPage<ManualReportDTO> page, @Param("ew") QueryWrapper<ManualReportDTO> queryWrapper);

    @Select("SELECT COUNT(s.sample_id) FROM soilsamplefull s LEFT JOIN MonitoringPoint m ON s.point_id = m.point_id ${ew.customSqlSegment}")
    Long countManualReportsWithPointName(@Param("ew") QueryWrapper<ManualReportDTO> queryWrapper);
}
