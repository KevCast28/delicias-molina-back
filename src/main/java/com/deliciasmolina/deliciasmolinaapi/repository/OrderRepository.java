package com.deliciasmolina.deliciasmolinaapi.repository;

import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
