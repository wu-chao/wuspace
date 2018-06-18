package com.github.wuchao.webproject.controller.admin.mainbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMailboxComposeController {

    @RequestMapping("/mailbox/compose")
    public String composeView() {
        return "mailbox/compose";
    }

    @PostMapping("/admin/compose")
    public String compose() {
        return "redirect:/admin/mailboxes";
    }

}
