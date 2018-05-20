package com.wuspace.controller.admin.mainbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MailboxIndexController {

    @RequestMapping("/mailbox")
    public String mailbox() {
        return "admin/mailbox/mailbox";
    }

    @RequestMapping("/mailbox/read")
    public String read() {
        return "admin/mailbox/read-mail";
    }

    @RequestMapping("/mailbox/compose")
    public String compose() {
        return "admin/mailbox/compose";
    }
}
