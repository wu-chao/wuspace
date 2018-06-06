package com.github.wuchao.webproject.controller.app.article;

import com.github.wuchao.webproject.domain.MediaInfo;
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
        List<MediaInfo> articles = new ArrayList() {{
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
            add(new MediaInfo());
        }};

        Page<MediaInfo> articlePage = new PageImpl(articles, pageable, 10);
        model.addAttribute("articles", articlePage);
        return "index";
    }
}
