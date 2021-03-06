package com.github.wuchao.webproject.service.news;

import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.domain.enums.MediaType;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

@Service
public class AdminNewsIndexService {

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    /**
     * 查询 News
     *
     * @return
     */
    public Page<MediaInfo> listNews(Pageable pageable) {
        Page<MediaInfo> news = mediaInfoRepository.findAll(Specifications
                .where((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder.equal(root.get("mediaType"), MediaType.NEWS);
                }), pageable);
        return news;
    }
}
