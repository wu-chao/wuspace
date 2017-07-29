package com.wuspace.controller.blogs;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogRepository;
import com.wuspace.domain.BlogSpecification;
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

    @RequestMapping("/blogs")
    public String index(@PageableDefault Pageable pageable, Model model) {
        Specification<Blog> blogSpecification = Specifications
                .where(BlogSpecification.findBlogs());
        Page<Blog> blogs = blogRepository.findAll(blogSpecification, pageable);

        model.addAttribute("blogs", blogs);

        return "blogs/index";
    }

    @RequestMapping("/blogs/{blogId}")
    public String show(@PathVariable("blogId") Long blogId, Model model) {
        return "blogs/show";
    }

    @RequestMapping("/blogs/compose")
    public String compose() {
        return "blogs/compose";
    }
}
