package com.elm.service;

import java.util.List;

import com.elm.pojo.Food;

public interface FoodService {
	List<Food> findFoodByBusinessId(Integer businessId);
	
}
