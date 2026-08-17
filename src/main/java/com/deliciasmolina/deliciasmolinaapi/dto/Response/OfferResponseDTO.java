package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OfferResponseDTO {
    private Long id;
    private String offerTitle;
    private String description;
    private BigDecimal discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive = true;
}
