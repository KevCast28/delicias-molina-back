package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.CategoryRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO getById(Long id);

    CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryRequestDTO);

    void delete(Long id);
}
