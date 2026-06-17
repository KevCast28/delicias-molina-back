package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getById(Long id);

    Order create(Order order);

    Order update(Long id, Order order);

    void detele(Long id);
}
