package com.elm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elm.pojo.Business;
import com.elm.service.BusinessService;
import com.elm.util.Result;

@RestController
@RequestMapping("BusinessController")
@CrossOrigin
public class BusinessController {
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping("/findBusinessByOrderTypeId")
	public Result findBusinessByOrderTypeId(Integer orderTypeId) {
		List<Business> businessList = businessService.findBusinessByOrderTypeId(orderTypeId);
		return Result.ok(businessList);
	}
	@RequestMapping("/findBusinessById")
	public Result findBusinessById(Integer businessId) {
		Business business = businessService.findBusinessById(businessId);
		return Result.ok(business);
	}

}
