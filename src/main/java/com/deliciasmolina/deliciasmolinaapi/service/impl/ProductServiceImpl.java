package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;
import com.deliciasmolina.deliciasmolinaapi.repository.CategoryRepository;
import com.deliciasmolina.deliciasmolinaapi.repository.ProductRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existing = getById(id);

        existing.setProductName(product.getProductName());
        existing.setDescription(product.getDescription());
        existing.setBasePrice(product.getBasePrice());
        existing.setImageUrl(product.getImageUrl());

//        Validate if category already exists
        if (existing.getCategory() != null) {
            Long categoryId = product.getCategory().getId();

            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            existing.setCategory(category);
        }

        existing.setOffer(product.getOffer());

        return productRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Product existing = getById(id);

        productRepository.delete(existing);
    }
}
