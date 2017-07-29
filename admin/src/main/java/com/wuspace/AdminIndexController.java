package com.wuspace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController {

    @RequestMapping("/admin")
    public String home() {
        return "home";
    }

    @RequestMapping("/admin/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/admin/index2")
    public String index2() {
        return "index2";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

}
