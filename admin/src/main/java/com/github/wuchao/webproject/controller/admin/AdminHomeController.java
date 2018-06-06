package com.github.wuchao.webproject.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping(value = {"", "/", "/index", "/home"})
    public String index() {
        return "index";
    }
}
