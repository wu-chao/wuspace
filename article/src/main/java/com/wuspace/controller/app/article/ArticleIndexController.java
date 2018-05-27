package com.wuspace.controller.app.article;

import com.wuspace.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleIndexController {

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        List<Article> articles = new ArrayList() {{
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
            add(new Article());
        }};

        Page<Article> articlePage = new PageImpl(articles, pageable, 10);
        model.addAttribute("articles", articlePage);
        return "index";
    }
}
