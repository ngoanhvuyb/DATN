package com.TungLam.service;

import com.TungLam.model.Category;

import java.util.List;

public interface CategoryService {


    public Category createCategory(String nam, Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
}
