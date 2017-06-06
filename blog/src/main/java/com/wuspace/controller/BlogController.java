package com.wuspace.controller;

import com.wuspace.domain.Blog;
import com.wuspace.domain.TopicType;
import com.wuspace.domain.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public String index(
            @RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
            @RequestParam(value = "prePage", defaultValue = "0") Integer prePage,
            @RequestParam(value = "curPage", defaultValue = "0") Integer curPage,
            Model model) {

        Pageable pageRequest = new PageRequest(curPage, 10, new Sort(Direction.DESC, "createdAt"));
        Page<Blog> blogPage = blogRepository.findAll(pageRequest);
        List<Blog> blogs = blogRepository.findAll();

        int totalPages = blogPage.getTotalPages();
        //判断分页按钮的显示方式
        if (firstResult <= 0) {
            firstResult = 0;
        }
        if (curPage - firstResult >= 5 && curPage <= totalPages - 1) {
            firstResult++;
        }
        if (curPage < firstResult && firstResult > 0) {
            firstResult--;
        }

        model.addAttribute("blogPage", blogPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstResult", firstResult);
        model.addAttribute("prePage", curPage);
        model.addAttribute("topicTypes", TopicType.getTopicTypes());

        return "blogs/index";
    }

}
