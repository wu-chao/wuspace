package com.wuspace.controller.blogs;

import com.wuspace.domain.Blog;
import com.wuspace.domain.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping(value = {"/", "/blogs"}, method = RequestMethod.GET)
    public String indexWithView(@PageableDefault Pageable pageable, Model model) {
        Page<Blog> blogs = listWithMarshalling(pageable);

        model.addAttribute("blogs", blogs);

        return "blogs/blogs";
    }

    @RequestMapping(value = "/blogs", produces = {"application/xml", "application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<Blog> listWithMarshalling(@PageableDefault Pageable pageable) {
        List<Blog> blogList = blogMapper.findAllByOrderByCreatedAtDesc();
        Page<Blog> blogs = new PageImpl<Blog>(blogList, pageable, blogList.size());

        return blogs;
    }

}
