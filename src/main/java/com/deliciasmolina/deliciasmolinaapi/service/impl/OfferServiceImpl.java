package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OfferRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OfferResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Offer;
import com.deliciasmolina.deliciasmolinaapi.exception.BadRequestException;
import com.deliciasmolina.deliciasmolinaapi.exception.DuplicateResourceException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.OfferMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.OfferRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;


    @Override
    public List<OfferResponseDTO> getAll() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::toResponse).toList();
    }

    @Override
    public OfferResponseDTO getById(Long id) {

        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        return OfferMapper.toResponse(offer);
    }

    @Override
    public OfferResponseDTO create(OfferRequestDTO offerRequestDTO) {

        if (offerRepository.existsByOfferTitleIgnoreCase(offerRequestDTO.getOfferTitle().trim())) {
            throw new DuplicateResourceException("Offer already exists");
        }

        if (offerRequestDTO.getEndDate().isBefore(offerRequestDTO.getStartDate())) {
            throw new BadRequestException("End date must be after start date");
        }

        Offer offer = OfferMapper.toEntity(offerRequestDTO);

        return OfferMapper.toResponse(offerRepository.save(offer));
    }

    @Override
    public OfferResponseDTO update(Long id, OfferRequestDTO offerRequestDTO) {

        Offer existing = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        if (offerRepository.existsByOfferTitleIgnoreCase(offerRequestDTO.getOfferTitle().trim()) && !existing.getOfferTitle()
                .equalsIgnoreCase(offerRequestDTO.getOfferTitle().trim())) {
            throw new DuplicateResourceException("Offer already exists");
        }

        if(offerRequestDTO.getEndDate().isBefore(offerRequestDTO.getStartDate())) {
            throw new BadRequestException("End date must be after start date");
        }

        OfferMapper.updateEntity(existing, offerRequestDTO);

        Offer updatedOffer = offerRepository.save(existing);

        return OfferMapper.toResponse(updatedOffer);
    }

    @Override
    public void delete(Long id) {
        Offer existing = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: " + id));

        offerRepository.delete(existing);
    }
}
