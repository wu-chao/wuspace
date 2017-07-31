package com.wuspace.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/admin/login")
    public String login() {
        return "admin/login";
    }
}
