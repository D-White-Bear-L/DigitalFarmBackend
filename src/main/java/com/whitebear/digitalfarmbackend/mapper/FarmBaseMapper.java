package com.whitebear.digitalfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.FarmBase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FarmBaseMapper extends BaseMapper<FarmBase> {
    // 这里可以定义特殊的查询方法
    // 基本的CRUD方法已由MyBatis-Plus提供
}
