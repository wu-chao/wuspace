package com.wuspace.service.article;

import com.wuspace.domain.Article;
import com.wuspace.repository.ArticleRepository;
import com.wuspace.service.article.dto.AdminArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminArticleIndexService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> listArticles(Pageable pageable){
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles;
    }
}
