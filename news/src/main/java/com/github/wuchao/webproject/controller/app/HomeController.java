package com.github.wuchao.webproject.controller.app;

import com.github.wuchao.webproject.service.HomeService;
import com.github.wuchao.webproject.service.dto.HomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index(@RequestParam(required = true, defaultValue = "1") Long categoryId,
                        @RequestParam(required = false) Long subCategoryId,
                        @RequestParam(required = false) String keyword,
                        @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        HomeDTO homeDTO = homeService.initHome(categoryId, subCategoryId, keyword, pageable);
        model.addAttribute("homeDTO", homeDTO);
        return "index";
    }
}
