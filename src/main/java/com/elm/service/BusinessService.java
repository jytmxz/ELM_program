package com.elm.service;

import java.util.List;

import com.elm.pojo.Business;

public interface BusinessService {

	List<Business> findBusinessByOrderTypeId(Integer orderTypeId);

	Business findBusinessById(Integer businessId);
}
