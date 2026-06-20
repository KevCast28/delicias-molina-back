package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponseDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
