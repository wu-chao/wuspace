package com.wuspace.controller.app.blogs;

import com.wuspace.controller.app.command.BlogCreateCommand;
import com.wuspace.domain.FormToken;
import com.wuspace.domain.MediaInfo;
import com.wuspace.domain.User;
import com.wuspace.repository.MediaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
public class BlogCreateController {

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @RequestMapping("/blogs/create")
    @FormToken(create = true)
    public String create() {
        return "blogs/create";
    }

    @PostMapping("/blogs/publish")
    @FormToken(recreate = true, remove = true)
    public String publish(@Valid BlogCreateCommand blogCreateCommand, BindingResult bindingResult,
                          @ModelAttribute("currentUser") User user, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogCreateCommand", blogCreateCommand);
            return "blogs/create";
        }

        String a = "hello";
        String b = "hello";
        System.out.println(a == b + "----------------");

        MediaInfo blog = new MediaInfo();
        blog = blogCreateCommand.toBlog(blog);
        blog.setAuthor(user);
        mediaInfoRepository.save(blog);

        return "blogs/blogs";
    }
}
