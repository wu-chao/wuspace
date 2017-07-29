package com.wuspace.controller.blogs;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminBlogIndexController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/blogs")
    public String index() {
        List<Blog> blogs = new ArrayList<>();
        return "blogs/index";
    }

    @RequestMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        return "blogs/show";
    }

    @RequestMapping("/blogs/compose")
    public String compose() {
        return "blogs/compose";
    }
}
