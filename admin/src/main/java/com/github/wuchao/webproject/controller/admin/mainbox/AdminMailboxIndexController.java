package com.github.wuchao.webproject.controller.admin.mainbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMailboxIndexController {

    @GetMapping("/mailboxes")
    public String mailbox() {
        return "mailbox/mailbox";
    }

    @GetMapping("/mailboxes/{mailId}")
    public String readMail(@PathVariable("mailId") Long mailId) {
        return "mailbox/read-mail";
    }


    @GetMapping("/mailbox/read-mail")
    public String read() {
        return "mailbox/read-mail";
    }

}
