package com.whitebear.digitalfarmbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // For Register and Login
    @Select("SELECT * FROM user WHERE (username = #{account} OR phone = #{account}) AND password = #{password} AND status = 'active'")
    User findByAccountAndPassword(String account, String password);

    @Select("SELECT COUNT(*) FROM user WHERE username = #{username}")
    int checkUsernameExists(String username);

    @Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
    int checkEmailExists(String email);

    @Select("SELECT * FROM user WHERE username = #{account} OR email = #{account} LIMIT 1")
    User findByAccount(@Param("account") String account);
}
