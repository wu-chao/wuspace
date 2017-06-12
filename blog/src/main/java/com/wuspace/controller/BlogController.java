package com.wuspace.controller;

import com.wuspace.domain.BlogMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping(value = {"/", "/blogs"}, method = RequestMethod.GET)
    public String index(Model model) {


        return "blogs/blogs";
    }

}
