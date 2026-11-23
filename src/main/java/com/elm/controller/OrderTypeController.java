package com.elm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elm.pojo.OrderType;
import com.elm.service.OrderTypeService;
import com.elm.util.Result;

@RestController
@RequestMapping("OrderTypeController")
@CrossOrigin
public class OrderTypeController {
	@Autowired
	private OrderTypeService orderTypeService;
	
	@RequestMapping("/findAll")
	public Result findAll() {
		List<OrderType> orderTypeList = orderTypeService.findOrderTypeAll();
		return Result.ok(orderTypeList);
		
	}
	
}
