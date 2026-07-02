package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ChangePasswordRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO getById(Long id);

    UserResponseDTO create(UserRequestDTO userRequestDTO);

    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO);

    void changePassword(Long id, ChangePasswordRequestDTO changePasswordRequestDTO);

    UserResponseDTO activate(Long id);

    UserResponseDTO deactivate(Long id);
}
