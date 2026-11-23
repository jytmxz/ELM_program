package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.elm.pojo.Food;
@Mapper
public interface FoodMapper {

	List<Food> findFoodByBusinessId(Integer businessId);

}
