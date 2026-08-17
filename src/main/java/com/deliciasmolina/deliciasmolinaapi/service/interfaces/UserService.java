package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ChangePasswordRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserCreateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserUpdateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO getById(Long id);

    UserResponseDTO create(UserCreateRequestDTO userCreateRequestDTO);

    UserResponseDTO update(Long id, UserUpdateRequestDTO userUpdateRequestDTO);

    void changePassword(Long id, ChangePasswordRequestDTO changePasswordRequestDTO);

    UserResponseDTO activate(Long id);

    UserResponseDTO deactivate(Long id);
}
