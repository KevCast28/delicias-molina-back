package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.User;
import com.deliciasmolina.deliciasmolinaapi.exception.DuplicateResourceException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.UserMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.UserRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findByIsActiveTrue()
                .stream().map(UserMapper::toResponse).toList();
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getIsActive()) {
            throw new ResourceNotFoundException("User not found");
        }

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        String name = userRequestDTO.getName().trim();

        String username = userRequestDTO.getUsername().trim();

        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new DuplicateResourceException("Username already exists");
        }

        userRequestDTO.setName(name);

        userRequestDTO.setUsername(username);

        User user = UserMapper.toEntity(userRequestDTO);

        User saved = userRepository.save(user);

        return UserMapper.toResponse(saved);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        String name = userRequestDTO.getName().trim();

        String username = userRequestDTO.getUsername().trim();

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (userRepository.existsByUsernameIgnoreCase(username) && !existing.getUsername().equalsIgnoreCase(username)) {
            throw new DuplicateResourceException("Username already exists");
        }

        userRequestDTO.setName(name);

        userRequestDTO.setUsername(username);

        UserMapper.updateEntity(existing, userRequestDTO);

        User updatedUser = userRepository.save(existing);

        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponseDTO activate(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setIsActive(true);

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponseDTO deactivate(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setIsActive(false);

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }
}
