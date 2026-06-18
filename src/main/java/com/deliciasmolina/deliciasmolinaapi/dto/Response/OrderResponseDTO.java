package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderResponseDTO {
    private Long id;
    private String clientName;
    private String telephone;
    private OrderType orderType;
    private BigDecimal quotedPrice;
    private LocalDate deliveryDate;
    private OrderStatus orderStatus;
}
