package com.github.wuchao.webproject.service.news.dto;

import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.domain.MediaInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class HomeDTO implements Serializable {

    /**
     * 所有目录分类
     */
    private List<Category> categories;

    /**
     * 当前目录分类
     */
    private Category currentCategory;

    /**
     * 轮播图URL
     */
    private List<String> bannerUrls;

    /**
     * 新闻文章等列表页内容
     */
    private Page<MediaInfo> mediaInfos;


}
