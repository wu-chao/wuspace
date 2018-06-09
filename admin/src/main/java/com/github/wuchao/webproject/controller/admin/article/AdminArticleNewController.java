package com.github.wuchao.webproject.controller.admin.article;

import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.repository.CategoryRepository;
import com.github.wuchao.webproject.service.article.AdminArticleService;
import com.github.wuchao.webproject.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminArticleNewController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdminArticleService adminArticleService;

    @GetMapping("/article/new")
    public String publishView(Model model) {
        // 获取栏目
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        AdminArticleDTO articleDTO = new AdminArticleDTO();
        articleDTO.setMediaType(MediaType.ARTICLE);
        model.addAttribute("articleDTO", articleDTO);

        return "article/new";
    }

    @PostMapping("/article/new")
    public String publish(AdminArticleDTO articleDTO) {
        adminArticleService.saveArticle(articleDTO);
        return "redirect:/admin/articles";
    }

}
