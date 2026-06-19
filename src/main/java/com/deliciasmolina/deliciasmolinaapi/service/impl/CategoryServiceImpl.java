package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.CategoryRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.CategoryResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.exception.DuplicateResourceException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.CategoryMapper;
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
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toResponse).toList();
    }

    @Override
    public CategoryResponseDTO getById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        return CategoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO) {

        String categoryName = categoryRequestDTO.getCategoryName().trim();

        if (categoryRepository.existsByCategoryNameIgnoreCase(categoryName)) {
            throw new DuplicateResourceException("Category already exists");
        }

        categoryRequestDTO.setCategoryName(categoryName);

        Category category = CategoryMapper.toEntity(categoryRequestDTO);

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toResponse(saved);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryRequestDTO) {

        Category existing = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if(categoryRepository.existsByCategoryNameIgnoreCase(categoryRequestDTO.getCategoryName()) && !existing.getCategoryName()
                .equalsIgnoreCase(categoryRequestDTO.getCategoryName())) {

            throw new DuplicateResourceException("Category already exists");
        }

        String categoryName = categoryRequestDTO.getCategoryName().trim();

        existing.setCategoryName(categoryName);

        Category updatedCategory = categoryRepository.save(existing);

        return CategoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        Category existing = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(existing);
    }
}
