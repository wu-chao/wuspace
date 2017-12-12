package com.wuspace.controller.blogs;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuspace.domain.Blog;
import com.wuspace.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    private BlogMapper blogMapper;

    @GetMapping(value = {"", "/", "/blogs"})
    public String index(@RequestParam(defaultValue = "0") int pageNum,
                        @RequestParam(defaultValue = "10") int pageSize, Model model) {
        PageInfo blogPageInfo = listWithMarshalling(pageNum, pageSize);
        model.addAttribute("blogPageInfo", blogPageInfo);
        return "blog/index";
    }

    @RequestMapping(value = "/blogs", produces = {"application/xml", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PageInfo listWithMarshalling(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogList = blogMapper.findAllWithUserByOrderByCreatedAtDesc();
        return new PageInfo(blogList);
    }


    /////////////////////////////////////// Querydsl ///////////////////////////////////////


//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@QuerydslPredicate(root = Blog.class) Predicate predicate,
//                        @PageableDefault Pageable pageable, Model model) {
//        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);
//        model.addAttribute("articles", blogs);
//        return "blog/index";
//    }

//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@PageableDefault Pageable pageable, Model model) {
//        QBlog qBlog = QBlog.blog;
//        Predicate predicate = qBlog.id.gt(2);
//        predicate = qBlog.title.containsIgnoreCase("spring").and(predicate);
//        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);
//        model.addAttribute("articles", blogs);
//        return "blog/index";
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
//        return "blog/index";
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
//        return "blog/index";
//    }
}
