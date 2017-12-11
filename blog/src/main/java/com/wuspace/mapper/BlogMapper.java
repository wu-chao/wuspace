package com.wuspace.mapper;

import com.wuspace.domain.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blogs as b order by b.created_date desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.wuspace.mapper.UserMapper.findUserById"))
    })
    List<Blog> findAllByOrderByCreatedAtDesc();

    @Select("select * from blogs as b where b.id = #{id}")
    @ResultMap(value = "com.wuspace.mapper.BlogMapper.blogMap")
    Blog findBlogWithUserById(Long id);
}
