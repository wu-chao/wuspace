package com.github.wuchao.webproject.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
