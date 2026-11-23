package com.elm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elm.pojo.Food;
import com.elm.service.FoodService;
import com.elm.util.Result;

@RestController
@RequestMapping("FoodController")
@CrossOrigin
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@RequestMapping("/findFoodByBusinessId")
	public Result findFoodByBusinessId(Integer businessId) {
		
		List<Food> foodList = foodService.findFoodByBusinessId(businessId);
		return Result.ok(foodList);
		
	}
	
}

