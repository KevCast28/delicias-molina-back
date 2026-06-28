package com.deliciasmolina.deliciasmolinaapi.dto.Response;

import com.deliciasmolina.deliciasmolinaapi.enums.UserRole;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String username;
    private UserRole userRole;
    private String token;
    private String tokenType = "Bearer";
}
