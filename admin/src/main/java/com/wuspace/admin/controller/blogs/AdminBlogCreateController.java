package com.wuspace.admin.controller.blogs;

import com.wuspace.admin.controller.BaseController;
import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminBlogCreateController extends BaseController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/blogs/create")
    public String create() {
        return "admin/blogs/create";
    }

    @RequestMapping(value = "/blogs/publish", method = RequestMethod.POST)
    public String publish(@Valid BlogCommmand blogCommmand, BindingResult bindingResult,
                          @ModelAttribute("user") Optional<User> user, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogCommmand", blogCommmand);
            return "admin/blogs/create";
        }

        if (user == null || !user.isPresent()) {
            throw new BadCredentialsException("");
        }

        Blog blog = new Blog();
        blog = blogCommmand.setBlog(blog);
//        blog.setUser(user.get());

        blogRepository.save(blog);

        return "redirect:/admin/blogs/index";
    }
}
