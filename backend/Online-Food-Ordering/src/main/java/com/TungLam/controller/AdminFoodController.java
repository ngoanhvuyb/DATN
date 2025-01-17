package com.TungLam.controller;

import com.TungLam.model.Food;
import com.TungLam.model.Restaurant;
import com.TungLam.model.User;
import com.TungLam.request.CreateFoodRequest;
import com.TungLam.response.MessageResponse;
import com.TungLam.service.FoodService;
import com.TungLam.service.RestaurantService;
import com.TungLam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService   restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader ("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        Food food=foodService.createFood(req, req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse res=new MessageResponse();
        res.setMessage("message deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                           @RequestHeader ("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
