package com.github.wuchao.webproject.service.news;

import com.github.wuchao.webproject.repository.MediaInfoRepository;
import com.github.wuchao.webproject.service.news.dto.NewsDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsShowService {

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    /**
     * @param newsId
     * @return
     */
    public NewsDetailDTO showNews(Long newsId) {
        NewsDetailDTO newsDetailDTO = new NewsDetailDTO();
        mediaInfoRepository.findOneById(newsId).ifPresent(mediaInfo -> newsDetailDTO.setMediaInfo(mediaInfo));
        return newsDetailDTO;
    }

}
