package com.wuspace.service.article;

import com.wuspace.domain.MediaInfo;
import com.wuspace.repository.MediaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminArticleIndexService {

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    public Page<MediaInfo> listArticles(Pageable pageable) {
        Page<MediaInfo> articles = mediaInfoRepository.findAll(pageable);
        return articles;
    }
}
