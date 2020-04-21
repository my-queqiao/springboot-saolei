package com.example.biz;

import java.util.List;

import com.example.pojo.Users;

public interface UserBiz {
	public List<Users> findAll();
	public void addUser(Users users);
}
