package com.wuspace.controller.admin.article;

import com.wuspace.domain.MediaInfo;
import com.wuspace.service.article.AdminArticleService;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminArticleEditController {

    @Autowired
    private AdminArticleService adminArticleService;

    @GetMapping("/articles/{articleId}/edit")
    public String editView(@PathVariable("articleId") Long articleId, Model model) {
        MediaInfo article = adminArticleService.showArticle(articleId);
        AdminArticleDTO adminArticleDTO = AdminArticleDTO.build(article);
        model.addAttribute("articleDTO", adminArticleDTO);
        return "article/edit";
    }

    @PutMapping("/articles/{articleId}")
    public String edit(@PathVariable("articleId") Long articleId,
                       AdminArticleDTO articleDTO) {
        adminArticleService.editArticle(articleId, articleDTO);
        return "redirect:/admin/articles";
    }

}
