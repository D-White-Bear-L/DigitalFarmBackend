package com.whitebear.digitalfarmbackend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whitebear.digitalfarmbackend.model.entity.AlertSettings;

@Mapper
public interface AlertSettingsMapper {

    @Select("SELECT * FROM AlertSettings")
    List<AlertSettings> findAllAlertSettings();

    @Update("UPDATE AlertSettings SET condition_type = #{conditionType}, threshold = #{threshold}, decimal_places = #{decimalPlaces}, step = #{step}, is_active = #{isActive}, update_time = NOW() WHERE setting_id = #{settingId}")
    int updateAlertSetting(AlertSettings alertSettings);
}
