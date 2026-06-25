package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ProductRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.ProductResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.entity.Offer;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAll();

    ProductResponseDTO getById(Long id);

    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO);

    void delete(Long id);
}
