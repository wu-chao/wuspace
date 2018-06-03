package com.wuspace.controller.admin.article;

import com.wuspace.domain.MediaInfo;
import com.wuspace.service.article.AdminArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminArticleIndexController {

    @Autowired
    private AdminArticleService adminArticleIndexService;

    @GetMapping(value = {"/articles", "/article/index"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<MediaInfo> articles = adminArticleIndexService.listArticles(pageable);
        model.addAttribute("articles", articles);

        return "article/index";
    }

}
