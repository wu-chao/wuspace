package com.wuspace.controller.blogs;

import com.wuspace.commons.domain.User;
import com.wuspace.controller.command.BlogCreateCommand;
import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
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
    private BlogRepository blogRepository;

    @RequestMapping("/blogs/create")
    public String create() {
        return "blogs/create";
    }

    @PostMapping("/blogs/publish")
    public String publish(@Valid BlogCreateCommand blogCreateCommand, BindingResult bindingResult,
                          @ModelAttribute("currentUser") User user, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogCreateCommand", blogCreateCommand);
            return "blogs/create";
        }

        Blog blog = new Blog();
        blog = blogCreateCommand.toBlog(blog);
        blog.setUser(user);
        blogRepository.save(blog);

        return "blogs/blogs";
    }
}
