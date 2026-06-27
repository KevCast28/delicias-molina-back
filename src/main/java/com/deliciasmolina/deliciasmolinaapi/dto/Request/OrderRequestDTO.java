package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderRequestDTO {
    @NotBlank
    private String clientName;
    @NotBlank
    private String telephone;
    @NotNull
    private OrderType orderType;
    private String flavor;
    private Integer peopleQuantity;
    private String imageReference;
    private String comments;
    @NotNull
    private LocalDate deliveryDate;
}
