package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.LoginRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.LoginResponseDTO;

public interface AuthenticationService {

    LoginResponseDTO login(LoginRequestDTO request);
}
