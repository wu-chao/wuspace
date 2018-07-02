package com.github.wuchao.webproject.mapper;

import com.github.wuchao.webproject.domain.MediaInfo;
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
                    one = @One(select = "com.github.wuchao.webproject.mapper.UserMapper.findUserById"))
    })
    Page<MediaInfo> listBlogPageByOrderByCreatedAtDesc();

    @Select("select * from blogs as b order by b.created_date desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.github.wuchao.webproject.mapper.UserMapper.findUserById"))
    })
    List<MediaInfo> findAllWithUserByOrderByCreatedAtDesc();

    @Select("select b.id as id, " +
            "b.title as title, " +
            "b.content as content, " +
            "u.id as user_id, " +
            "u.nickname as nickname " +
            "from blogs as b, users as u where b.id = #{id} and u.id = b.user_id ")
//    @ResultMap("com.github.wuchao.webproject.mapper.BlogMapper.blogMap")
    MediaInfo findBlogWithUserById(Long id);
}
