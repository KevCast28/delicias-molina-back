package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.OrderDetailRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.OrderDetailResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.Order;
import com.deliciasmolina.deliciasmolinaapi.entity.OrderDetail;
import com.deliciasmolina.deliciasmolinaapi.entity.Product;
import com.deliciasmolina.deliciasmolinaapi.exception.ResourceNotFoundException;
import com.deliciasmolina.deliciasmolinaapi.mapper.OrderDetailMapper;
import com.deliciasmolina.deliciasmolinaapi.repository.OrderDetailRepository;
import com.deliciasmolina.deliciasmolinaapi.repository.OrderRepository;
import com.deliciasmolina.deliciasmolinaapi.repository.ProductRepository;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderDetailResponseDTO> getAll() {
        return orderDetailRepository.findAll()
                .stream().map(OrderDetailMapper::toResponse).toList();
    }

    @Override
    public OrderDetailResponseDTO getById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order detail not found with id: " + id));

        return OrderDetailMapper.toResponse(orderDetail);
    }

    @Override
    public OrderDetailResponseDTO create(OrderDetailRequestDTO orderDetailRequestDTO) {
        Product product = productRepository.findById(orderDetailRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderDetailRequestDTO.getProductId()));

        Order order = orderRepository.findById(orderDetailRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderDetailRequestDTO.getOrderId()));

        OrderDetail orderDetail = OrderDetailMapper.toEntity(orderDetailRequestDTO, product, order);

        orderDetail.setUnitPrice(product.getBasePrice());
        orderDetail.setSubtotal(product.getBasePrice().multiply(BigDecimal.valueOf(orderDetailRequestDTO.getQuantity())));


        OrderDetail saved = orderDetailRepository.save(orderDetail);

        return OrderDetailMapper.toResponse(saved);
    }

    @Override
    public OrderDetailResponseDTO update(Long id, OrderDetailRequestDTO orderDetailRequestDTO) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order detail not found with id: " + id));

        Product product = productRepository.findById(orderDetailRequestDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderDetailRequestDTO.getProductId()));

        Order order = orderRepository.findById(orderDetailRequestDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id:" + orderDetailRequestDTO.getOrderId()));

        OrderDetailMapper.updateEntity(existing, orderDetailRequestDTO, product, order);

        existing.setUnitPrice(product.getBasePrice());

        existing.setSubtotal(product.getBasePrice().multiply(BigDecimal.valueOf(orderDetailRequestDTO.getQuantity())));

        OrderDetail updatedOrderDetail = orderDetailRepository.save(existing);

        return OrderDetailMapper.toResponse(updatedOrderDetail);
    }

    @Override
    public void delete(Long id) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order detail not found with id: " + id));

        orderDetailRepository.delete(existing);
    }
}
