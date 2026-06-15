package com.deliciasmolina.deliciasmolinaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "Order is required")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product is required")
    private Product product;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @NotNull(message = "Price is required")
    private BigDecimal unitPrice;
    @NotNull(message = "Subtotal is required")
    private BigDecimal subtotal;
}
