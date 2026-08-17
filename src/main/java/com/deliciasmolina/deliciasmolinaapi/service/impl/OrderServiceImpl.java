package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderItemRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderUpdateRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import com.deliciasmolina.deliciasmolinaapi.entity.OrderDetail;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderStatus;
import com.deliciasmolina.deliciasmolinaapi.enums.OrderType;
import com.deliciasmolina.deliciasmolinaapi.exception.BadRequestException;
import com.deliciasmolina.deliciasmolinaapi.exception.DuplicateResourceException;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.OrderMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.OrderRepository;
import com.deliciasmolina.deliciasmolinaapi.repository.ProductRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

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

            if (orderRequestDTO.getItems() != null && !orderRequestDTO.getItems().isEmpty()) {
                throw new BadRequestException("Custom orders cannot contain products");
            }

            order.setCustomQuotedPrice(null);
        } else {
            order.setFlavor(null);
            order.setPeopleQuantity(null);
            order.setImageReference(null);
            order.setComments(null);

            if (orderRequestDTO.getItems() == null || orderRequestDTO.getItems().isEmpty()) {
                throw new BadRequestException("Order must contain at least one product");
            }

            Set<Long> productIds = new HashSet<>();

            BigDecimal total = BigDecimal.ZERO;

            for (OrderItemRequestDTO item : orderRequestDTO.getItems()) {

                if (!productIds.add(item.getProductId())) {
                    throw new DuplicateResourceException("Duplicate products are not allowed in the same order");
                }

                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + item.getProductId()));

                OrderDetail detail = new OrderDetail();

                detail.setProduct(product);

                detail.setQuantity(item.getQuantity());

                detail.setUnitPrice(product.getBasePrice());

                BigDecimal subtotal = product.getBasePrice().multiply(BigDecimal.valueOf(item.getQuantity()));

                detail.setSubtotal(subtotal);

                total = total.add(subtotal);

                order.addOrderDetail(detail);
            }

            order.setTotal(total);
        }

        if (order.getDeliveryDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the past");
        }

        order.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderUpdateRequestDTO orderUpdateRequestDTO) {
        Order existing = orderRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        existing.setClientName(orderUpdateRequestDTO.getClientName().trim());

        existing.setTelephone(orderUpdateRequestDTO.getTelephone().trim());

        existing.setComments(orderUpdateRequestDTO.getComments());

        existing.setDeliveryDate(orderUpdateRequestDTO.getDeliveryDate());

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
