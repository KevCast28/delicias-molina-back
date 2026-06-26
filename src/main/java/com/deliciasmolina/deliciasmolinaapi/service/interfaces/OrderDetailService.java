package com.deliciasmolina.deliciasmolinaapi.service.interfaces;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderDetailRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderDetailResponseDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponseDTO> getAll();

    OrderDetailResponseDTO getById(Long id);

    OrderDetailResponseDTO create(OrderDetailRequestDTO orderDetailRequestDTO);

    OrderDetailResponseDTO update(Long id, OrderDetailRequestDTO orderDetailRequestDTO);

    void delete(Long id);
}
