package com.wuspace.controller.admin.article;

import com.wuspace.domain.Article;
import com.wuspace.service.article.AdminArticlePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminArticlePublishController {

    @Autowired
    private AdminArticlePublishService articlePublishService;

    @GetMapping("/articles/publish")
    public String publishView() {
        return "article/publish";
    }

    @PostMapping("/articles/publish")
    public String publish(Article article) {
        articlePublishService.publish(article);
        return "redirect:/admin/articles";
    }

}
