package com.wuspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {


    @RequestMapping(value = {"/", "/blogs"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "blogs/index";
    }

}
