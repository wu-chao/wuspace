package com.wuspace.controller.mainbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu-chao on 7/22/17.
 */
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
