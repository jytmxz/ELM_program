package com.elm.service;

import java.util.List;
import java.util.Map;

import com.elm.pojo.Cart;

public interface CartService {

	List<Cart> findCartByUserIdAndBusinessId(String userId, Integer businessId);

	Map<Integer,Integer> findCartSumByUserIdGroupBusinessId(String userId);

}
