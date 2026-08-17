package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private String clientName;
    private String telephone;
    private OrderType orderType;
    private String flavor;
    private Integer peopleQuantity;
    private String imageReference;
    private String comments;
    private BigDecimal customQuotedPrice;
    private BigDecimal total;
    private LocalDate deliveryDate;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDTO> items;
}
