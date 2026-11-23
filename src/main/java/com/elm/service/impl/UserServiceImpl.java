package com.elm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elm.mapper.UserMapper;

import com.elm.pojo.User;
import com.elm.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String userId, String password) {
		User user = userMapper.login(userId,password);
		return user;
	}

}
