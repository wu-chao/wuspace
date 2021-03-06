package com.github.wuchao.webproject.service.category;

import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.repository.CategoryRepository;
import com.github.wuchao.webproject.service.category.dto.AdminCategoryDTO;
import com.github.wuchao.webproject.domain.Category;
import com.github.wuchao.webproject.repository.CategoryRepository;
import com.github.wuchao.webproject.service.category.dto.AdminCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public AdminCategoryDTO initCategoryDTO() {
        List<Category> categories = categoryRepository.findAll();

        AdminCategoryDTO categoryDTO = new AdminCategoryDTO();
        categoryDTO.setParentCategories(categories);
        return categoryDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCategory(AdminCategoryDTO categoryDTO) {
        categoryRepository.save(categoryDTO.getCategory());
    }
}
