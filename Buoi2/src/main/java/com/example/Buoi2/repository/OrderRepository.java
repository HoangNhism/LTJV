package com.example.Buoi2.repository;

import com.example.Buoi2.model.Category;
import com.example.Buoi2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
