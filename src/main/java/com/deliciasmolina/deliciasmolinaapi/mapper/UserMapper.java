package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.User;

public final class UserMapper {

    private UserMapper() {}

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();

        updateEntity(user, dto);

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

    public static void updateEntity(User user, UserRequestDTO dto) {

        user.setName(dto.getName().trim());
        user.setUsername(dto.getUsername().trim());
        user.setPassword(dto.getPassword());
        user.setUserRole(dto.getUserRole());
        user.setIsActive(dto.getIsActive());
    }
}
