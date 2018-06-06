package com.github.wuchao.webproject.service.category.dto;

import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminCategoryDTO {

    private Category category;

    private List<Category> parentCategories;
}
