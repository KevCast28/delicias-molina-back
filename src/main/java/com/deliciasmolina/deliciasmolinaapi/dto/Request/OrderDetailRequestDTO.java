package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailRequestDTO {
    @NotNull(message = "Order is required")
    private Long orderId;
    @NotNull(message = "Product is required")
    private Long productId;
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
}
