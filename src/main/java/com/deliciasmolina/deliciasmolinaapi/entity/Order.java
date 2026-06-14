package com.deliciasmolina.deliciasmolinaapi.entity;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Client name is required")
    private String clientName;
    @NotBlank(message = "Contact number is required")
    private String telephone;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Order type is required")
    private OrderType orderType;
    private BigDecimal quotedPrice;
    private String flavor;
    private Integer peopleQuantity;
    private String referenceImage;
    private String comments;
    private Date deliveryDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
