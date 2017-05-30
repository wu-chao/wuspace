package com.wuspace.controller;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chao on 17-5-29.
 */
@Controller
public class HomeController {
    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(@PageableDefault Pageable pageable, Model model) {
        Page<Blog> blogs = blogRepository.findAllByOrderByCreatedAtDesc(pageable);
        long totalBlogNum = blogs.getTotalElements();
        model.addAttribute("blogs", blogs);
        model.addAttribute("totalBlogNum", totalBlogNum);
        return "blogs/blogs";
    }
}
