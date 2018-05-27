package com.wuspace.controller.admin.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCategoryIndexController {

    @GetMapping("/categories")
    public String index() {
        return "category/index";
    }

}
