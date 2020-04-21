package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biz.UserBiz;
import com.example.mappers.UsersMapper;
import com.example.pojo.Users;

@Service
@Transactional //本类的所有方法都添加事物。
public class UserBizImpl implements UserBiz{
	@Autowired
	private UsersMapper userMapper;
	@Override
	public List<Users> findAll() {
		List<Users> list = userMapper.selectAll();
		return list;
	}
	@Override
	public void addUser(Users users) {
		userMapper.insert(users);
	}
}
