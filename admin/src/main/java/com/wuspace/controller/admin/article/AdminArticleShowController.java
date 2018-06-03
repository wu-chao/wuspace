package com.wuspace.controller.admin.article;

import com.wuspace.domain.MediaInfo;
import com.wuspace.service.article.AdminArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminArticleShowController {

    @Autowired
    private AdminArticleService adminArticleIndexService;

    @GetMapping("/articles/{articleId}")
    public String index(@PathVariable("articleId") Long articleId, Model model) {
        MediaInfo article = adminArticleIndexService.showArticle(articleId);
        model.addAttribute("article", article);

        return "article/show";
    }
}
