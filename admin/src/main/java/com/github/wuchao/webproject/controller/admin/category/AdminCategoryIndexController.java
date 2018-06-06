package com.github.wuchao.webproject.controller.admin.category;

import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.repository.CategoryRepository;
import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCategoryIndexController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

}
