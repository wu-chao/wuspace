package com.wuspace.commons.mapper;

import com.wuspace.commons.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("select u from users u where u.id = #{id}")
    Optional<User> findUserById(Long id);

    @Select("select u from users as u where u.username like #{username}")
    Optional<User> findUserByUsername(String username);

    @Select("select u.id id, u.username username, u.activated activated from users as u where u.username = #{username}")
    @Results({
            @Result(column = "id", property = "id", javaType = Long.class),
            @Result(column = "username", property = "username"),
            @Result(column = "activated", property = "activated"),
            @Result(column = "id", property = "authorities", many = @Many(select = "com.wuspace.domain.AuthorityMapper.findAuthoritesById", fetchType = FetchType.LAZY))
    })
    User findOneWithAuthoritiesByUsername(String username);
}
