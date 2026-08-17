package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderUpdateRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 80)
    private String clientName;
    @NotBlank(message = "Telephone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Telephone must contain exactly 10 numbers")
    private String telephone;
    @Size(max = 500)
    private String comments;
    @NotNull(message = "Delivery date is required")
    private LocalDate deliveryDate;

}
