package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OfferRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OfferResponseDTO;

import java.util.List;

public interface OfferService {

    List<OfferResponseDTO> getAll();

    OfferResponseDTO getById(Long id);

    OfferResponseDTO create(OfferRequestDTO offerRequestDTO);

    OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO);

    void delete(Long id);
}
