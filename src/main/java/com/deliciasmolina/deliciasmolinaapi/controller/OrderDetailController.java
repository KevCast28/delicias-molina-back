package com.deliciasmolina.deliciasmolinaapi.controller;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderDetailRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderDetailResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/oder-details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailResponseDTO>> getAll() {
        return ResponseEntity.ok(orderDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDetailResponseDTO> create(@Valid @RequestBody OrderDetailRequestDTO orderDetailRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.create(orderDetailRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrderDetailRequestDTO orderDetailRequestDTO) {
        return ResponseEntity.ok(orderDetailService.update(id, orderDetailRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderDetailService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
