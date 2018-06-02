package com.wuspace.service.article.dto;

import com.wuspace.domain.enumeration.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminArticleDTO {

    private Long categoryId;

    private String title;

    private String content;

    private MediaType mediaType;

}
