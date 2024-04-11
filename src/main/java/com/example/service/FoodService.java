package com.example.service;

import com.example.dto.RestaurantDto;
import com.example.model.Category;
import com.example.model.Food;
import com.example.model.Restaurant;
import com.example.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegiterian, boolean isNonVeg, boolean isSeasonal, String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception;

}
