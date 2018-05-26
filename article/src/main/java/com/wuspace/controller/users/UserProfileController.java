package com.wuspace.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserProfileController {

    @RequestMapping("/users/profile")
    public String profile() {

        return "users/profile";
    }
}
