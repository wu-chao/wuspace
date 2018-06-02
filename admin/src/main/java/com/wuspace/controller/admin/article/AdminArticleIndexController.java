package com.wuspace.controller.admin.article;

import com.wuspace.domain.Article;
import com.wuspace.domain.Works;
import com.wuspace.repository.WorksRepository;
import com.wuspace.service.article.AdminArticleIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminArticleIndexController {

    @Autowired
    private AdminArticleIndexService adminArticleIndexService;

    @Autowired
    private WorksRepository worksRepository;

    @GetMapping(value = {"/articles", "/article/index"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<Article> articles = adminArticleIndexService.listArticles(pageable);
        model.addAttribute("articles", articles);

        List<Works> worksList = worksRepository.findAll();
        return "article/index";
    }
}
