package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;
    private String productName;
    private String description;
    private BigDecimal basePrice;
    private String imageUrl;
    private String categoryName;
    private String offerTitle;
}
