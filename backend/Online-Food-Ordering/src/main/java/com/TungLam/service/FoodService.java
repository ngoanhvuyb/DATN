package com.TungLam.service;

import com.TungLam.model.Category;
import com.TungLam.model.Food;
import com.TungLam.model.Restaurant;
import com.TungLam.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    List<Food> getRestaurantsFood(Long restaurantId,
                                  boolean isVegetarian,    // Changed here
                                  boolean isNonveg,
                                  boolean isSeasonal,
                                  String foodCategory);

    List<Food> searchFood(String keyword);

    Food findFoodById(Long foodId) throws Exception;

    Food updateAvailabilityStatus(Long foodId) throws Exception;  // Changed here

}
