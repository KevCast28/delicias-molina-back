package com.deliciasmolina.deliciasmolinaapi.controller;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.ChangePasswordRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserCreateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.UserUpdateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.UserResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userCreateRequestDTO));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok(userService.update(id, userUpdateRequestDTO));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        userService.changePassword(id, changePasswordRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserResponseDTO> activate(@PathVariable Long id) {
        return  ResponseEntity.ok(userService.activate(id));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<UserResponseDTO> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deactivate(id));
    }
}
