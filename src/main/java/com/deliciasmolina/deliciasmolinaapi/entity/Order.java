package com.deliciasmolina.deliciasmolinaapi.entity;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Client name is required")
    private String clientName;
    @NotBlank(message = "Contact number is required")
    private String telephone;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order type is required")
    private OrderType orderType;
    private BigDecimal quotedPrice;
    private String flavor;
    private Integer peopleQuantity;
    private String imageReference;
    private String comments;
    @NotNull(message = "Date is required")
    private LocalDate deliveryDate;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private OrderStatus orderStatus = OrderStatus.PENDING;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
