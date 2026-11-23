package com.elm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elm.mapper.BusinessMapper;
import com.elm.pojo.Business;
import com.elm.service.BusinessService;
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
	@Autowired
	private BusinessMapper businessMapper;
	
	@Override
	public List<Business> findBusinessByOrderTypeId(Integer orderTypeId) {
		List<Business> businessList = businessMapper.findBusinessByOrderTypeId(orderTypeId);
		return businessList;
	}
	
	@Override
	public Business findBusinessById(Integer businessId) {
		Business business = businessMapper.findBusinessById(businessId);
		return business;
	}

}
