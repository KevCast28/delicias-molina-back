package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserCreateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserUpdateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.User;

public final class UserMapper {

    private UserMapper() {}

    public static User toEntity(UserCreateRequestDTO dto) {
        User user = new User();

        user.setName(dto.getName().trim());
        user.setUsername(dto.getUsername().trim());
        user.setPassword(dto.getPassword().trim());
        user.setUserRole(dto.getUserRole());

        return user;
    }

    public static UserResponseDTO toResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setUserRole(user.getUserRole());
        dto.setIsActive(user.getIsActive());

        return dto;
    }
}
