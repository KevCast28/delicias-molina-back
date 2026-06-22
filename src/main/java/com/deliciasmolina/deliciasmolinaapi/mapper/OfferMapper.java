package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OfferRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OfferResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Offer;

public final class OfferMapper {

    private OfferMapper() {}

    public static Offer toEntity(OfferRequestDTO dto) {
        Offer offer = new Offer();

        updateEntity(offer, dto);
        
        return offer;
    }

    public static OfferResponseDTO toResponse(Offer offer) {
        OfferResponseDTO dto = new OfferResponseDTO();

        dto.setId(offer.getId());
        dto.setOfferTitle(offer.getOfferTitle());
        dto.setDescription(offer.getDescription());
        dto.setDiscountPercentage(offer.getDiscountPercentage());
        dto.setStartDate(offer.getStartDate());
        dto.setEndDate(offer.getEndDate());
        dto.setIsActive(offer.getIsActive());

        return dto;
    }

    public static void updateEntity(Offer offer, OfferRequestDTO dto) {

        offer.setOfferTitle(dto.getOfferTitle().trim());
        offer.setDescription(dto.getDescription());
        offer.setDiscountPercentage(dto.getDiscountPercentage());
        offer.setStartDate(dto.getStartDate());
        offer.setEndDate(dto.getEndDate());
        offer.setIsActive(dto.getIsActive());
    }
}
