package com.example.service;

import com.example.model.Category;
import com.example.model.Food;
import com.example.model.Restaurant;
import com.example.repository.FoodRepository;
import com.example.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food= new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegiterian(req.isVegiterian());

        Food savedFood=foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegiterian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        List<Food> foods=foodRepository.findByRestaurantId(restaurantId);
        if(isVegiterian){
            foods=filterByVegiterian(foods,isVegiterian);
        }
        if(isNonVeg){
            foods=filterByNonveg(foods,isNonVeg);
        }
        if(isSeasonal){
            foods=filterBySeasonal(foods,isSeasonal);
            
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods=fiterByCategory(foods,foodCategory);
        }
        return foods;
    }

    private List<Food> fiterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> food.isVegiterian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegiterian(List<Food> foods, boolean isVegiterian) {
        return foods.stream().filter(food -> food.isVegiterian()==isVegiterian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood=foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("food not exist");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
