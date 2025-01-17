package com.TungLam.repository;

import com.TungLam.model.Order;
import com.TungLam.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
