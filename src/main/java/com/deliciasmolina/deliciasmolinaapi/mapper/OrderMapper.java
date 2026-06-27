package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;

public final class OrderMapper {

    private OrderMapper() {}

    public static Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();

        updateEntity(order, dto);

        return order;
    }

    public static OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());
        dto.setClientName(order.getClientName());
        dto.setTelephone(order.getTelephone());
        dto.setOrderType(order.getOrderType());
        dto.setFlavor(order.getFlavor());
        dto.setPeopleQuantity(order.getPeopleQuantity());
        dto.setImageReference(order.getImageReference());
        dto.setComments(order.getComments());
        dto.setQuotedPrice(order.getQuotedPrice());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setOrderStatus(order.getOrderStatus());

        return dto;
    }

    public static void updateEntity(Order order, OrderRequestDTO dto) {

        order.setClientName(dto.getClientName().trim());
        order.setTelephone(dto.getTelephone().trim());
        order.setOrderType(dto.getOrderType());
        order.setFlavor(dto.getFlavor() != null ? dto.getFlavor().trim() : null);
        order.setPeopleQuantity(dto.getPeopleQuantity());
        order.setImageReference(dto.getImageReference() != null ? dto.getImageReference().trim() : null);
        order.setComments(dto.getComments() != null ? dto.getComments().trim() : null);
        order.setDeliveryDate(dto.getDeliveryDate());
    }
}
