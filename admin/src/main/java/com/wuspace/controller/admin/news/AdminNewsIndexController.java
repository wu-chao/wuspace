package com.wuspace.controller.admin.news;

import com.wuspace.domain.MediaInfo;
import com.wuspace.service.news.AdminNewsIndexService;
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
public class AdminNewsIndexController {

    @Autowired
    private AdminNewsIndexService adminNewsIndexService;

    @GetMapping(value = {"/news", "/news/index"})
    public String index(@PageableDefault Pageable pageable, Model model) {
        Page<MediaInfo> news = adminNewsIndexService.listNews(pageable);
        model.addAttribute("news", news);

        return "news/index";
    }
}
