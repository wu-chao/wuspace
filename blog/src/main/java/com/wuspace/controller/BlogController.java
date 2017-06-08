package com.wuspace.controller;

import com.wuspace.domain.Blog;
import com.wuspace.domain.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping(value = {"/", "/blogs"}, method = RequestMethod.GET)
    public String index(Model model) {
        Set<Blog> blogs = blogMapper.findAllByOrderByCreatedAtDesc();

        return "blogs/index";
    }

}
