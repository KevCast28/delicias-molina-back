package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderDetailRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderDetailResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import com.deliciasmolina.deliciasmolinaapi.entity.OrderDetail;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;

public final class OrderDetailMapper {

    private OrderDetailMapper() {}

    public static OrderDetail toEntity(OrderDetailRequestDTO dto, Product product, Order order)  {
        OrderDetail orderDetail = new OrderDetail();

        updateEntity(orderDetail, dto, product, order);

        return orderDetail;
    }

    public static OrderDetailResponseDTO toResponse(OrderDetail orderDetail) {
        OrderDetailResponseDTO dto = new OrderDetailResponseDTO();

        dto.setId(orderDetail.getId());
        dto.setOrderId(orderDetail.getOrder().getId());
        dto.setProductId(orderDetail.getProduct().getId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setUnitPrice(orderDetail.getUnitPrice());
        dto.setSubtotal(orderDetail.getSubtotal());

        return dto;
    }

    public static void updateEntity(OrderDetail orderDetail, OrderDetailRequestDTO dto, Product product, Order order) {

        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail.setQuantity(dto.getQuantity());
    }
}
