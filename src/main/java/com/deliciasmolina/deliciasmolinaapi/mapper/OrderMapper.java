package com.deliciasmolina.deliciasmolinaapi.mapper;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderItemResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;

public final class OrderMapper {

    private OrderMapper() {}

    public static Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();

        order.setClientName(dto.getClientName().trim().replaceAll("\\s+", " "));

        order.setTelephone(dto.getTelephone().trim().replaceAll("\\s+", " "));

        order.setOrderType(dto.getOrderType());

        order.setFlavor(dto.getFlavor() != null ? dto.getFlavor().trim().replaceAll("\\s+", " ") : null);

        order.setPeopleQuantity(dto.getPeopleQuantity());

        order.setImageReference(dto.getImageReference() != null ? dto.getImageReference().trim().replaceAll("\\s+", " ") : null);

        order.setComments(dto.getComments() != null ? dto.getComments().trim().replaceAll("\\s+", " ") : null);

        order.setDeliveryDate(dto.getDeliveryDate());

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
        dto.setCustomQuotedPrice(order.getCustomQuotedPrice());
        dto.setTotal(order.getTotal());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setItems(
                order.getOrderDetails().stream().map(detail -> {
                    OrderItemResponseDTO item = new OrderItemResponseDTO();

                    item.setProductId(detail.getProduct().getId());
                    item.setProductName(detail.getProduct().getProductName());
                    item.setQuantity(detail.getQuantity());
                    item.setUnitPrice(detail.getUnitPrice());
                    item.setSubtotal(detail.getSubtotal());

                    return item;
                }).toList()
        );

        return dto;
    }
}
