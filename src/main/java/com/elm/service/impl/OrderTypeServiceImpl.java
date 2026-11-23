package com.elm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elm.mapper.OrderTypeMapper;
import com.elm.pojo.OrderType;
import com.elm.service.OrderTypeService;
@Service
@Transactional
public class OrderTypeServiceImpl implements OrderTypeService {
	@Autowired
	private OrderTypeMapper orderTypeMapper;
	@Override
	public List<OrderType> findOrderTypeAll() {
		List<OrderType> orderList = orderTypeMapper.findOrderTypeAll();
		return orderList;
	}

}
