package com.wuspace.mapper;

import com.wuspace.domain.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface BlogMapper {
    //.offset(pageable.getOffset())
    //                .limit(pageable.getPageSize())
//                .orderBy(qBlog.createdDate.desc())
    @Select("select * from blogs as b order by b.created_date desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.wuspace.mapper.UserMapper.findUserById"))
    })
    Page<Article> listBlogPageByOrderByCreatedAtDesc();

    @Select("select * from blogs as b order by b.created_date desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.wuspace.mapper.UserMapper.findUserById"))
    })
    List<Article> findAllWithUserByOrderByCreatedAtDesc();

    @Select("select b.id as id, " +
            "b.title as title, " +
            "b.content as content, " +
            "u.id as user_id, " +
            "u.nickname as nickname " +
            "from blogs as b, users as u where b.id = #{id} and u.id = b.user_id ")
//    @ResultMap("com.wuspace.mapper.BlogMapper.blogMap")
    Article findBlogWithUserById(Long id);
}
