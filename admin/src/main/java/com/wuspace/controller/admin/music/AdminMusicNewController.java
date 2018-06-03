package com.wuspace.controller.admin.music;

import com.wuspace.domain.Category;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.repository.CategoryRepository;
import com.wuspace.service.article.AdminArticleService;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMusicNewController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdminArticleService adminArticleService;

    @GetMapping("/music/new")
    public String publishView(Model model) {
        // 获取栏目
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        AdminArticleDTO articleDTO = new AdminArticleDTO();
        articleDTO.setMediaType(MediaType.ARTICLE);
        model.addAttribute("articleDTO", articleDTO);

        return "music/new";
    }

    @PostMapping("/music/new")
    public String publish(AdminArticleDTO articleDTO) {
        adminArticleService.saveArticle(articleDTO);
        return "redirect:/admin/music";
    }

}
