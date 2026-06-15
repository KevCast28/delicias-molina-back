package com.deliciasmolina.deliciasmolinaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String productName;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private BigDecimal basePrice;
    @NotBlank(message = "Image is required")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category is required")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
