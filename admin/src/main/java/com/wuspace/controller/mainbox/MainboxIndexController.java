package com.wuspace.controller.mainbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainboxIndexController {

    @RequestMapping("/mailbox")
    public String mailbox() {
        return "mailbox/mailbox";
    }

    @RequestMapping("/mailbox/read")
    public String read() {
        return "mailbox/read";
    }

    @RequestMapping("/mailbox/compose")
    public String compose() {
        return "mailbox/compose";
    }
}
