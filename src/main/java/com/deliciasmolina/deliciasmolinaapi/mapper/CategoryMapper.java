package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.CategoryRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.CategoryResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Category;

public final class CategoryMapper {

    private CategoryMapper() {}

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();

        category.setCategoryName(dto.getCategoryName());

        return category;
    }

    public static CategoryResponseDTO toResponse(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();

        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());

        return dto;
    }
}
