package com.wuspace.controller.blogs;

import com.wuspace.domain.BlogMapper;
import com.wuspace.domain.Blog;
import com.wuspace.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogShowController {

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        Blog blog = blogMapper.findBlogById(blogId);
        if (blog == null) {
            throw new ResourceNotFoundException("blog 不存在");
        }

        model.addAttribute("article", blog);

        return "blogs/show";
    }
}
