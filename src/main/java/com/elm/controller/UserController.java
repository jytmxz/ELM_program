package com.elm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elm.pojo.User;
import com.elm.service.UserService;
import com.elm.util.Result;

@RestController
@RequestMapping("UserController")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public Result login(String userId,String password){
		User user = userService.login(userId, password);
		if(user != null) {
			return Result.ok(user);
		}else {
			return Result.no();
		}
		
	}
}
