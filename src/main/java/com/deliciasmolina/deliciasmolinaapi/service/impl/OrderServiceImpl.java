package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
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
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order create(Order order) {


        if (order.getOrderType() == OrderType.CUSTOM) {
            if (order.getFlavor() == null || order.getFlavor().isBlank()) {
                throw new RuntimeException("Flavor is required for custom orders");
            }

            if (order.getPeopleQuantity() == null) {
                throw new RuntimeException("People quantity is required for custom orders");
            }

//          Price set for custom orders is initially null
            order.setQuotedPrice(null);
        }

        if (order.getDeliveryDate() == null) {
            throw new RuntimeException("Delivery date is required");
        }

        if (order.getDeliveryDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Delivery date cannot be in the past");
        }

        if (order.getOrderStatus() == null) {
            order.setOrderStatus(OrderStatus.PENDING);
        }

        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        Order existing = getById(id);

        existing.setClientName(order.getClientName());
        existing.setTelephone(order.getTelephone());
        existing.setDeliveryDate(order.getDeliveryDate());
        existing.setOrderStatus(order.getOrderStatus());

        return orderRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Order exists = getById(id);

        orderRepository.delete(exists);
    }
}
