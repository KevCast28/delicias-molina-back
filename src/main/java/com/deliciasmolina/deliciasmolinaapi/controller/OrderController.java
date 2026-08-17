package com.deliciasmolina.deliciasmolinaapi.controller;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderUpdateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(orderRequestDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrderUpdateRequestDTO orderUpdateRequestDTO) {
        return ResponseEntity.ok(orderService.update(id, orderUpdateRequestDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
