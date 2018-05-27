package com.wuspace.service.category.dto;

import com.wuspace.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminCategoryDTO {

    private Category category;

    private List<Category> parentCategories;
}
