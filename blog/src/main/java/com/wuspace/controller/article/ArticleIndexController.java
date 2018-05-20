package com.wuspace.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleIndexController {

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index() {

        return "index";
    }
}
