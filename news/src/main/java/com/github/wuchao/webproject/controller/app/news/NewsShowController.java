package com.github.wuchao.webproject.controller.app.news;

import com.github.wuchao.webproject.service.news.NewsShowService;
import com.github.wuchao.webproject.service.news.dto.NewsDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NewsShowController {

    @Autowired
    private NewsShowService newsShowService;

    @GetMapping("/news/{newsId}")
    public String show(@PathVariable("newsId") Long newsId, Model model) {
        NewsDetailDTO newsDetailDTO = newsShowService.showNews(newsId);
        model.addAttribute("newsDetailDTO", newsDetailDTO);
        return "news/show";
    }

}
