package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusRequestDTO {

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;
}
