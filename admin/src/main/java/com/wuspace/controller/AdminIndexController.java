package com.wuspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController {

    @RequestMapping("/admin")
    public String home() {
        return "admin/home";
    }

    @RequestMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/admin/index2")
    public String index2() {
        return "admin/index2";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "admin/calendar";
    }

}
