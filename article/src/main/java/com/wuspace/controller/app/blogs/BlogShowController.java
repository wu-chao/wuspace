package com.wuspace.controller.app.blogs;

import com.wuspace.domain.Article;
import com.wuspace.exception.ResourceNotFoundException;
import com.wuspace.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogShowController {

    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        Article blog = blogMapper.findBlogWithUserById(blogId);
        if (blog == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("blog", blog);

        return "blog/show";
    }

//    @RequestMapping("/dsl-blogs/{blogId}")
//    public String dslShow(@PathVariable("blogId") Long blogId, Model model) {
//        QBlog qBlog = QBlog.blog;
//        Predicate predicate = qBlog.id.eq(blogId);
//        Blog blog = blogRepository.findOne(predicate);
//
//        if (blog == null) {
//            throw new ResourceNotFoundException();
//        }
//
//        model.addAttribute("article", blog);
//
//        return "blog/show";
//    }
}
