package com.wuspace.controller.blogs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogMapper;
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
    private JPAQueryFactory queryFactory;

    @GetMapping(value = {"/articles"})
    public String articles(@PageableDefault Pageable pageable, Model model) {
        Page<Blog> articles = listWithMarshalling(pageable);

        model.addAttribute("articles", articles);

        return "blogs/articles";
    }

    @GetMapping(value = {"", "/", "/blogs"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<Blog> blogs = listWithMarshalling(pageable);

        model.addAttribute("articles", blogs);

        return "blogs/index";
    }

    @RequestMapping(value = "/blogs", produces = {"application/xml", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Blog> listWithMarshalling(@PageableDefault Pageable pageable) {
        List<Blog> blogList = blogMapper.findAllByOrderByCreatedAtDesc();
        Page<Blog> blogs = new PageImpl<Blog>(blogList, pageable, blogList.size());

        return blogs;
    }


    /////////////////////////////////////// Querydsl ///////////////////////////////////////


//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@QuerydslPredicate(root = Blog.class) Predicate predicate,
//                        @PageableDefault Pageable pageable, Model model) {
//        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);
//        model.addAttribute("articles", blogs);
//        return "blogs/index";
//    }

//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@PageableDefault Pageable pageable, Model model) {
//        QBlog qBlog = QBlog.blog;
//        Predicate predicate = qBlog.id.gt(2);
//        predicate = qBlog.title.containsIgnoreCase("spring").and(predicate);
//        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);
//        model.addAttribute("articles", blogs);
//        return "blogs/index";
//    }

//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@PageableDefault Pageable pageable, Model model) {
//        QBlog qBlog = QBlog.blog;
//
//        List<Blog> blogList = queryFactory.selectFrom(qBlog)
//                .where(qBlog.title.containsIgnoreCase("spring"))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(qBlog.createdDate.desc())
//                .fetch();
//        Long totalElements = queryFactory.selectFrom(qBlog).where(qBlog.title.containsIgnoreCase("spring")).fetchCount();
//        Page<Blog> blogs = new PageImpl<Blog>(blogList, pageable, totalElements);
//
//        model.addAttribute("articles", blogs);
//
//        return "blogs/index";
//    }

//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@QuerydslPredicate(root = Blog.class) Predicate predicate,
//                        @PageableDefault Pageable pageable, Model model) {
//        QBlog qBlog = QBlog.blog;
//
//        List<Blog> blogList = queryFactory.selectFrom(qBlog)
//                .where(predicate)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(qBlog.createdDate.desc())
//                .fetch();
//
//        Long totalElements = queryFactory.selectFrom(qBlog).where(predicate).fetchCount();
//        Page<Blog> blogs = new PageImpl<Blog>(blogList, pageable, totalElements);
//
//        model.addAttribute("articles", blogs);
//
//        return "blogs/index";
//    }
}
