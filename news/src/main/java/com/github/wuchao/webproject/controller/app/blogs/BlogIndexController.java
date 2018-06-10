package com.github.wuchao.webproject.controller.app.blogs;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.mapper.BlogMapper;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/blogs", produces = {"application/xml", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PageInfo listWithMarshalling(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MediaInfo> blogList = blogMapper.findAllWithUserByOrderByCreatedAtDesc();
        return new PageInfo(blogList);
    }

    private void loadBlogs(Model model) {

    }


    /////////////////////////////////////// Querydsl ///////////////////////////////////////


//    @GetMapping(value = {"", "/", "home", "/index", "/index.html"})
//    public String index(@QuerydslPredicate(root = Blog.class) Predicate predicate,
//                        @PageableDefault Pageable pageable, Model model) {
//        Page<Blog> blogs = blogRepository.findAll(predicate, pageable);
//        model.addAttribute("articles", blogs);
//        return "blog/index";
//    }

//    @GetMapping(value = {"/index.html"})
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
