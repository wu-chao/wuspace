package com.wuspace.controller.blogs;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogMapper;
import com.wuspace.domain.QBlog;
import com.wuspace.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @GetMapping(value = {"", "/", "/blogs"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<Blog> blogs = listWithMarshalling(pageable);

        model.addAttribute("articles", blogs);

        return "blogs/blogs";
    }

    @RequestMapping(value = "/blogs", produces = {"application/xml", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Blog> listWithMarshalling(@PageableDefault Pageable pageable) {
        List<Blog> blogList = blogMapper.findAllByOrderByCreatedAtDesc();
        Page<Blog> blogs = new PageImpl<Blog>(blogList, pageable, blogList.size());

        return blogs;
    }


    @GetMapping(value = {"/dsl-blogs"})
    public String dslBlogs(@PageableDefault Pageable pageable, Model model) {
        QBlog qBlog = QBlog.blog;

        //分页1
        Predicate predicate = qBlog.id.gt(JPAExpressions.select(qBlog.id).from(qBlog).where(qBlog.id.eq(Long.valueOf(2))));
        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);

        // 分页2
//        JPAQuery<?> query = jpaQueryFactory.from(qBlog)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(qBlog.createdDate.desc());
//        Page<Blog> blogs = new PageImpl<>((List<Blog>) query.fetch(), pageable, query.fetchCount());



        model.addAttribute("articles", blogs);

        return "blogs/blogs";
    }

}
