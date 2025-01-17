package com.TungLam.repository;

import com.TungLam.model.Cart;
import com.TungLam.model.CartItem;
import com.TungLam.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndFood(Cart cart, Food food);

}
