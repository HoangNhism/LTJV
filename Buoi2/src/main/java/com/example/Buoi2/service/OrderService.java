package com.example.Buoi2.service;

import com.example.Buoi2.model.CartItem;
import com.example.Buoi2.model.Category;
import com.example.Buoi2.model.Order;
import com.example.Buoi2.model.OrderDetail;
import com.example.Buoi2.repository.OrderDetailRepository;
import com.example.Buoi2.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    @Transactional
    public Order createOrder(Order order, List<CartItem> cartItems) {
        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        // Optionally clear the cart after order placement
        cartService.clearCart();
        return order;
    }
}
