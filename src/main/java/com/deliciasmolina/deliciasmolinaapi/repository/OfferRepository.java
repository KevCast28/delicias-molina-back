package com.deliciasmolina.deliciasmolinaapi.repository;

import com.deliciasmolina.deliciasmolinaapi.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    boolean existsByOfferTitleIgnoreCase(String offerTitle);
}
