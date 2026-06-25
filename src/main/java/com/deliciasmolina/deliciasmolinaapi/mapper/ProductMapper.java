package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ProductRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.ProductResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.entity.Offer;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;

public final class ProductMapper {

    private ProductMapper() {}

    public static Product toEntity(ProductRequestDTO dto, Category category, Offer offer) {
        Product product = new Product();

        updateEntity(product, dto, category, offer);

        return product;
    }

    public static ProductResponseDTO toResponse(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setBasePrice(product.getBasePrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategoryName(product.getCategory().getCategoryName());
        dto.setOfferTitle(product.getOffer() != null ? product.getOffer().getOfferTitle() : null);

        return dto;
    }

    public static void updateEntity(Product product, ProductRequestDTO dto, Category category, Offer offer) {

        product.setProductName(dto.getProductName().trim());
        product.setDescription(dto.getDescription());
        product.setBasePrice(dto.getBasePrice());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);
        product.setOffer(offer);
    }
}
