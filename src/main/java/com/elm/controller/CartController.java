package com.elm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elm.pojo.Cart;
import com.elm.service.CartService;
import com.elm.util.Result;

@RestController
@RequestMapping("CartController")
@CrossOrigin
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/findCartByUserIdAndBusinessId")
	public Result findCartByUserIdAndBusinessId(String userId,Integer businessId) {
		List<Cart> cartList = cartService.findCartByUserIdAndBusinessId(userId, businessId);
		return Result.ok(cartList);
	}
	
	@RequestMapping("/findCartSumByUserIdGroupBusinessId")
	public Result findCartSumByUserIdGroupBusinessId(String userId) {
		Map<Integer, Integer> map = cartService.findCartSumByUserIdGroupBusinessId(userId);
		System.out.println(map);
		return Result.ok(map);
	}
	


}
