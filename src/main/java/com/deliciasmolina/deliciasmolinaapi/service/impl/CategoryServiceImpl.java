package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.repository.CategoryRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Category not found"));
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category existing = getById(id);

        existing.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Category existing = getById(id);

        categoryRepository.delete(existing);
    }
}
