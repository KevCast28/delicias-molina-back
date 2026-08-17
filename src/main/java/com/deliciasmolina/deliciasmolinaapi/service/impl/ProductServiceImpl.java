package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ProductRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.ProductResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Category;
import com.deliciasmolina.deliciasmolinaapi.entity.Offer;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;
import com.deliciasmolina.deliciasmolinaapi.exception.DuplicateResourceException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.ProductMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.CategoryRepository;
import com.deliciasmolina.deliciasmolinaapi.repository.OfferRepository;
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
    private final OfferRepository offerRepository;

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
                .stream().map(ProductMapper::toResponse).toList();
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        String productName = productRequestDTO.getProductName().trim();

        if (productRepository.existsByProductNameIgnoreCase(productName)) {
            throw new DuplicateResourceException("Product already exists");
        }

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequestDTO.getCategoryId()));

        Offer offer = null;

        if (productRequestDTO.getOfferId() != null) {
            offer = offerRepository.findById(productRequestDTO.getOfferId())
                    .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + productRequestDTO.getOfferId()));
        }

        productRequestDTO.setProductName(productName);

        Product product = ProductMapper.toEntity(productRequestDTO, category, offer);

        Product saved = productRepository.save(product);

        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        String productName = productRequestDTO.getProductName().trim();

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        if (productRepository.existsByProductNameIgnoreCase(productName) && !existing.getProductName().equalsIgnoreCase(productName)) {
            throw new DuplicateResourceException("Product already exists");
        }

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productRequestDTO.getCategoryId()));

        Offer offer = null;

        if (productRequestDTO.getOfferId() != null) {
            offer = offerRepository.findById(productRequestDTO.getOfferId())
                    .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + productRequestDTO.getOfferId()));
        }

        productRequestDTO.setProductName(productName);

        ProductMapper.updateEntity(existing, productRequestDTO, category, offer);

        Product updatedProduct = productRepository.save(existing);

        return ProductMapper.toResponse(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(existing);
    }
}
