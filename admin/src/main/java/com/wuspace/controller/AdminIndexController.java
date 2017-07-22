package com.wuspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

}
