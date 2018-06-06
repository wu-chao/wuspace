package com.github.wuchao.webproject.service.article;

import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.exception.ResourceNotFoundException;
import com.github.wuchao.webproject.repository.MediaContentRepository;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import com.github.wuchao.webproject.service.article.dto.AdminArticleDTO;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import com.github.wuchao.webproject.exception.ResourceNotFoundException;
import com.github.wuchao.webproject.repository.MediaContentRepository;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import com.github.wuchao.webproject.service.article.dto.AdminArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AdminArticleService {

    @Autowired
    private MediaContentRepository mediaContentRepository;

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    /**
     * 查询 Articles
     *
     * @param pageable
     * @return
     */
    public Page<MediaInfo> listArticles(Pageable pageable) {
        Page<MediaInfo> articles = mediaInfoRepository.findAll(Specifications
                .where((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder.equal(root.get("mediaType"), MediaType.ARTICLE);
                }), pageable);
        return articles;
    }

    /**
     * 查询 Article
     *
     * @param articleId
     * @return
     */
    public MediaInfo showArticle(Long articleId) {
        Optional<MediaInfo> mediaInfoOptional = mediaInfoRepository.findByIdAndMediaType(articleId, MediaType.ARTICLE);
        if (mediaInfoOptional.isPresent()) {
            return mediaInfoOptional.get();
        } else {
            log.warn("Object MediaInfo(id = {}, mediaType= {}) not found.", articleId, MediaType.ARTICLE);
            throw new ResourceNotFoundException();
        }
    }

    /**
     * 保存 Article
     *
     * @param articleDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(AdminArticleDTO articleDTO) {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo = articleDTO.initMediaInfo(mediaInfo);
        mediaContentRepository.save(mediaInfo.getMediaContent());
        mediaInfoRepository.save(mediaInfo);
    }

    /**
     * 修改 Article
     *
     * @param articleId
     * @param articleDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void editArticle(Long articleId, AdminArticleDTO articleDTO) {
        MediaInfo article = showArticle(articleId);
        articleDTO.initMediaInfo(article);
    }

}
