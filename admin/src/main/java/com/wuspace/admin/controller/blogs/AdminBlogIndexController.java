package com.wuspace.admin.controller.blogs;

import com.wuspace.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminBlogIndexController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    @RequestMapping("/blogs")
    public String index(@PageableDefault Pageable pageable, Model model) {
        Specification<Blog> blogSpecification = Specifications
                .where(BlogSpecification.findBlogs());
        Page<Blog> blogs = blogRepository.findAll(blogSpecification, pageable);

        model.addAttribute("blogs", blogs);

        return "admin/blogs/index";
    }

    @RequestMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        return "admin/blogs/show";
    }
}
