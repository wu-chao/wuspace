package com.github.wuchao.webproject.controller.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserProfileController {

    @GetMapping("/user/profile")
    public String profile() {
        return "user/profile";
    }

}
