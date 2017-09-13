package com.wuspace.commons.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("select u from users as u where u.id = #{id}")
    Optional<User> findUserById(Long id);

    @Select("select u from users as u where u.username like #{username}")
    Optional<User> findUserByUsername(String username);

    @Select("select u from users as u where u.username like #{username}")

    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
