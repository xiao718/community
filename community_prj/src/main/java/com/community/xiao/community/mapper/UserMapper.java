package com.community.xiao.community.mapper;

import com.community.xiao.community.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from gituser where token=#{token}")
    User selectByToken(@Param("token") String token);
}
