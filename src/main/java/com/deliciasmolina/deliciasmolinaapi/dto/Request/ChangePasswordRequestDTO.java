package com.deliciasmolina.deliciasmolinaapi.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequestDTO {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).*$", message = "Password must contain at least one uppercase and one number")
    private String newPassword;
}
