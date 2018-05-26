package com.wuspace.service.article;

import com.wuspace.domain.Article;
import com.wuspace.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminArticlePublishService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void publish(Article article) {

        articleRepository.save(article);
    }
}
