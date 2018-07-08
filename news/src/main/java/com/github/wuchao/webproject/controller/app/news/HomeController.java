package com.github.wuchao.webproject.controller.app.news;

import com.github.wuchao.webproject.service.news.HomeService;
import com.github.wuchao.webproject.service.news.dto.HomeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(@RequestParam(defaultValue = "1") Long categoryId,
                        @RequestParam(required = false) Long subCategoryId,
                        @RequestParam(required = false) String keyword,
                        @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {

        HomeDTO homeDTO = homeService.initHome(categoryId, subCategoryId, keyword, pageable);
        model.addAttribute("homeDTO", homeDTO);

        return "news/home";
    }
}
