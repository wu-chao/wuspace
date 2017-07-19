package com.wuspace.domain;

import org.apache.ibatis.annotations.*;

import java.util.Set;

@Mapper
public interface BlogMapper {

    @Select("select * from blogs as b order by b.created_at desc")
    @Results({
            @Result(property = "user",
                    column = "user_id",
                    one = @One(select = "com.wuspace.domain.UserMapper.findUserById"))
    })
    Set<Blog> findAllByOrderByCreatedAtDesc();

    @Select("select * from blogs as b where b.id = #{id}")
    @Results({
            @Result(property = "user",
                    column = "user_id",
                    one = @One(select = "com.wuspace.domain.UserMapper.findUserById"))
    })
    Blog findBlogById(Long id);
}
