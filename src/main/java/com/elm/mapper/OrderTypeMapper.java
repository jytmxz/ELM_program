package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.elm.pojo.OrderType;
@Mapper
public interface OrderTypeMapper {
	List<OrderType> findOrderTypeAll();
}
