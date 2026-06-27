package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import com.deliciasmolina.deliciasmolinaapi.exception.BadRequestException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.OrderMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.OrderRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponseDTO> getAll() {
        return orderRepository.findAll()
                .stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponseDTO getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO) {
        Order order = OrderMapper.toEntity(orderRequestDTO);

        if (order.getOrderType() == OrderType.CUSTOM) {
            if (order.getFlavor() == null || order.getFlavor().isBlank()) {
                throw new BadRequestException("Flavor is required for custom orders");
            }

            if (order.getPeopleQuantity() == null) {
                throw new BadRequestException("People quantity is required for custom orders");
            }

            order.setQuotedPrice(null);
        } else {
            order.setFlavor(null);
            order.setPeopleQuantity(null);
            order.setImageReference(null);
            order.setComments(null);
        }

        if (order.getDeliveryDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the past");
        }

        order.setOrderStatus(OrderStatus.PENDING);

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO update(Long id, OrderRequestDTO orderRequestDTO) {
        Order existing = orderRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        OrderMapper.updateEntity(existing, orderRequestDTO);

        if (existing.getOrderType() == OrderType.CUSTOM) {
            if (existing.getFlavor() == null || existing.getFlavor().isBlank()) {
                throw new BadRequestException("Flavor is required");
            }

            if (existing.getPeopleQuantity() == null) {
                throw new BadRequestException("People quantity is required");
            }

            existing.setQuotedPrice(null);
        } else {
            existing.setFlavor(null);
            existing.setPeopleQuantity(null);
            existing.setImageReference(null);
            existing.setComments(null);
        }

        if (existing.getDeliveryDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the past");
        }

        Order updated = orderRepository.save(existing);

        return OrderMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Order existing = orderRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        orderRepository.delete(existing);
    }
}
