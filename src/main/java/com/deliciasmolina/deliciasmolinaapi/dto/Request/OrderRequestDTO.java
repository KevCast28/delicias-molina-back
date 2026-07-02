package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 80)
    private String clientName;
    @NotBlank(message = "Telephone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Telephone must contain exactly 10 numbers")
    private String telephone;
    @NotNull(message = "Order type is required")
    private OrderType orderType;
    @Size(max = 50)
    private String flavor;
    @Positive(message = "Quantity must be greater than zero")
    private Integer peopleQuantity;
    @Size(max = 255)
    private String imageReference;
    @Size(max = 500)
    private String comments;
    @NotNull(message = "Delivery date is required")
    private LocalDate deliveryDate;
}
