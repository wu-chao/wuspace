package com.wuspace.controller.admin.category;

import com.wuspace.service.category.AdminCategoryService;
import com.wuspace.service.category.dto.AdminCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCategoryNewController {

    @Autowired
    private AdminCategoryService categoryService;

    @GetMapping("/categories/new")
    public String createView(Model model) {
        AdminCategoryDTO categoryDTO = categoryService.initCategoryDTO();
        model.addAttribute("categoryDTO", categoryDTO);
        return "category/new";
    }

    @PostMapping("/categories/new")
    public String create(AdminCategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return "redirect:/admin/categories";
    }

}
