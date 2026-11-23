package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.elm.pojo.Business;

@Mapper
public interface BusinessMapper {
	List<Business> findBusinessByOrderTypeId(Integer orderTypeId);

	Business findBusinessById(Integer businessId);
}
