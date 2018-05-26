package com.wuspace.controller.admin.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminArticleIndexController {

    @GetMapping(value = {"/articles", "/articles/index"})
    public String index() {
        return "article/index";
    }
}
