package com.github.wuchao.webproject.service.news.dto;

import com.github.wuchao.webproject.domain.MediaInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsDetailDTO {

    private MediaInfo mediaInfo;

    private List<MediaInfo> hotPosts;


}
