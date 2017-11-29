package com.wuspace.mapper;

import com.wuspace.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("select * from users u where u.id = #{id}")
    User findUserById(Long id);

    @Select("select u from users as u where u.username like #{username}")
    Optional<User> findUserByUsername(String username);

    @Select("select u.id id, u.username username, u.activated activated from users as u where u.username = #{username}")
    @Results({ //可以定义在xml文件中,然后使用@ResultMap注解引用
            @Result(id = true, property = "id", column = "id", javaType = Long.class),
            @Result(property = "username", column = "username"),
            @Result(property = "activated", column = "activated"),
            @Result(property = "authorities", column = "name", many = @Many(select = "com.wuspace.domain.AuthorityMapper.findAuthoritesByUsername", fetchType = FetchType.LAZY))
    })
    User findOneWithAuthoritiesByUsername(String username);
}
