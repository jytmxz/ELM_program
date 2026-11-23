package com.elm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elm.mapper.CartMapper;
import com.elm.pojo.Cart;
import com.elm.service.CartService;
@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public List<Cart> findCartByUserIdAndBusinessId(String userId, Integer businessId) {
		List<Cart> cartList = cartMapper.findCartByUserIdAndBusinessId(userId, businessId);
		return cartList;
	}

	@Override
	public Map<Integer, Integer> findCartSumByUserIdGroupBusinessId(String userId) {
		Map<Integer, Integer> map = cartMapper.findCartSumByUserIdGroupBusinessId(userId);
		
		return map;
	}

}
