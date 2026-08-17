package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import com.deliciasmolina.deliciasmolinaapi.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;
    @NotNull(message = "Role is required")
    private UserRole userRole;
}
