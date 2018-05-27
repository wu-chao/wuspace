package com.wuspace.service.category;

import com.wuspace.domain.Category;
import com.wuspace.repository.CategoryRepository;
import com.wuspace.service.category.dto.AdminCategoryDTO;
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
