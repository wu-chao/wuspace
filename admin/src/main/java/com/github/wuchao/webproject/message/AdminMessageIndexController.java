package com.github.wuchao.webproject.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMessageIndexController {

    @GetMapping("/messages")
    public String messages() {
        return "message/index";
    }
}
