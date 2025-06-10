package com.whitebear.digitalfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.SoilSampleFull;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataAnalysisMapper extends BaseMapper<SoilSampleFull> {
} 