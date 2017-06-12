package com.wuspace.domain;

import com.wuspace.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface BlogMapper {

    @Select("select distinct b.id, b.created_at from blogs as b order by b.created_at desc")
    Set<Blog> findAllByOrderByCreatedAtDesc();
}
