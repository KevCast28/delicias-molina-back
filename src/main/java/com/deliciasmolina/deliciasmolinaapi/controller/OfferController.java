package com.deliciasmolina.deliciasmolinaapi.controller;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OfferRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OfferResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getAll() {
        return ResponseEntity.ok(offerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OfferResponseDTO> create(@Valid @RequestBody OfferRequestDTO offerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offerService.create(offerRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OfferRequestDTO offerRequestDTO) {
        return ResponseEntity.ok(offerService.update(id, offerRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
