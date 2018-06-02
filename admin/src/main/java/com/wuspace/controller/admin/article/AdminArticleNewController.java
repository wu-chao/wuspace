package com.wuspace.controller.admin.article;

import com.wuspace.domain.Category;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.repository.CategoryRepository;
import com.wuspace.service.article.AdminArticlePublishService;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminArticleNewController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdminArticlePublishService articlePublishService;

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

    // @PostMapping(value = "/article/new", params = {"mediaType=ARTICLE"})
    @PostMapping("/article/new")
    public String publish(AdminArticleDTO articleDTO) {
        articlePublishService.publishArticle(articleDTO);
        return "redirect:/admin/articles";
    }

}
