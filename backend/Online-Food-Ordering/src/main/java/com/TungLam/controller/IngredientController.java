package com.TungLam.controller;


import com.TungLam.model.IngredientCategory;
import com.TungLam.model.IngredientsItem;
import com.TungLam.request.IngredientCategoryRequest;
import com.TungLam.request.IngredientRequest;
import com.TungLam.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

@PostMapping("/category")
    private ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception{
        IngredientCategory item=ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    private ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req
    ) throws Exception{
        IngredientsItem item=ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientsItem> updateIngredientStoke(
            @PathVariable Long id
    ) throws Exception{
        IngredientsItem item=ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception{
        List<IngredientsItem> items=ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception{
        List<IngredientCategory> items=ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
