package com.whitebear.digitalfarmbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whitebear.digitalfarmbackend.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM User WHERE (username = #{account} OR phone = #{account}) AND password = #{password} AND status = 'active'")
    User findByAccountAndPassword(String account, String password);

    @Select("SELECT COUNT(*) FROM User WHERE username = #{username}")
    int checkUsernameExists(String username);

    @Select("SELECT COUNT(*) FROM User WHERE email = #{email}")
    int checkEmailExists(String email);

    @Select("SELECT * FROM user WHERE username = #{account} OR email = #{account} LIMIT 1")
    User findByAccount(@Param("account") String account);
}
