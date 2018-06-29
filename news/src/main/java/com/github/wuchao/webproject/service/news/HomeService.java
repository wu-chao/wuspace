package com.github.wuchao.webproject.service.news;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.domain.MediaInfo;
import com.github.wuchao.webproject.repository.CategoryRepository;
import com.github.wuchao.webproject.repository.MediaInfoRepository;
import com.github.wuchao.webproject.service.news.dto.HomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MediaInfoRepository mediaInfoRepository;

    @Cached(expire = 60, name = "HomeService.")
    @CacheRefresh(refresh = 50)
    public HomeDTO initHome(Long categoryId, Long subCategoryId, String keyword, Pageable pageable) {
        HomeDTO homeDTO = new HomeDTO();
        List<Category> categories = listCategories();
        homeDTO.setCurrentCategory(categories.get(0));
        homeDTO.setCategories(categories);
        homeDTO.setMediaInfos(listMediaInfos(pageable));
        return homeDTO;
    }

    public List<Category> listCategories() {
        return categoryRepository.findAllBy();
    }

    public Page<MediaInfo> listMediaInfos(Pageable pageable) {
        Page<MediaInfo> mediaInfos = mediaInfoRepository.findAll(pageable);
        return mediaInfos;
    }


}
