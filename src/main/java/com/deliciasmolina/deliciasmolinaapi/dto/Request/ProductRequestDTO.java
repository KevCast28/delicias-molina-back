package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotBlank
    private String productName;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal basePrice;
    @NotBlank
    private String imageUrl;
    @NotNull
    private Long categoryId;
    private Long offerId;
}
