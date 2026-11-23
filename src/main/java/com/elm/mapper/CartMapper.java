package com.elm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.elm.pojo.Cart;
@Mapper
public interface CartMapper {

	List<Cart> findCartByUserIdAndBusinessId(String userId, Integer businessId);

	Map<Integer,Integer> findCartSumByUserIdGroupBusinessId(String userId);

}
