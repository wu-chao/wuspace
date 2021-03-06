package com.github.wuchao.webproject.controller.admin.video;

import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.service.article.AdminArticleService;
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
public class AdminVideoIndexController {

    @Autowired
    private AdminArticleService adminArticleIndexService;

    @GetMapping(value = {"/videos", "/video/index"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<MediaInfo> articles = adminArticleIndexService.listArticles(pageable);
        model.addAttribute("articles", articles);

        return "video/index";
    }
}
