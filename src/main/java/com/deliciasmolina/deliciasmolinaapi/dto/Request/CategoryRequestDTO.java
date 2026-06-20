package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required")
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ]+$",
            message = "Category name contains invalid characters"
    )
    private String categoryName;
}
