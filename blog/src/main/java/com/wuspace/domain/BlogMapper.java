package com.wuspace.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blogs b order by b.created_date desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.wuspace.mapper.UserMapper.findUserById"))
    })
    List<Blog> findAllByOrderByCreatedAtDesc();

    @Select("select * from blogs b where b.id = #{id}")
    @Results({
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.wuspace.mapper.UserMapper.findUserById"))
    })
    Blog findBlogById(Long id);
}
