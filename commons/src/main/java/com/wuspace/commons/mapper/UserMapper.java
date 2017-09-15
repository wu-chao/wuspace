package com.wuspace.commons.mapper;

import com.wuspace.commons.domain.User;
import org.apache.ibatis.annotations.Mapper;
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
