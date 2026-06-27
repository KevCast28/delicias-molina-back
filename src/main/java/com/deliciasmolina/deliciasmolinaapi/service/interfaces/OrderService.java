package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> getAll();

    OrderResponseDTO getById(Long id);

    OrderResponseDTO create(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO update(Long id, OrderRequestDTO orderRequestDTO);

    void delete(Long id);
}
