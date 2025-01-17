package com.TungLam.service;

import com.TungLam.model.IngredientCategory;
import com.TungLam.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId)throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id )throws Exception;
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)throws Exception;
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId)throws Exception;

    public IngredientsItem updateStock(Long id)throws Exception;
}
