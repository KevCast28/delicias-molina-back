package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import com.deliciasmolina.deliciasmolinaapi.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 12)
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255)
    private String password;
    @NotNull(message = "Role is required")
    private UserRole userRole;
}
