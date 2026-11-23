package com.elm.service;

import com.elm.pojo.User;

public interface UserService {

	User login(String userId, String password);

}
