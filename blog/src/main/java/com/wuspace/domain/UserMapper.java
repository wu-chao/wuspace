package com.wuspace.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from users as u where u.id = #{id}")
    public User findUserById(Long id);
}
