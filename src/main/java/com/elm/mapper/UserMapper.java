package com.elm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.elm.pojo.User;
@Mapper
public interface UserMapper {

	User login(String userId, String password);

}
