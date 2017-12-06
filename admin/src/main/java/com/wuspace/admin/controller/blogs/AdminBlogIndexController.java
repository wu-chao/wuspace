package com.wuspace.admin.controller.blogs;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogSpecs;
import com.wuspace.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminBlogIndexController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/blogs")
    public String index(@RequestParam("keyword") String titleOrContent,
                        @PageableDefault Pageable pageable, Model model) {
        Specification<Blog> blogSpec = BlogSpecs.filter(titleOrContent);
        Page<Blog> blogs = blogRepository.findAll(blogSpec, pageable);

        model.addAttribute("blogs", blogs);

        return "admin/blogs/index";
    }

    @RequestMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        return "admin/blogs/show";
    }
}
