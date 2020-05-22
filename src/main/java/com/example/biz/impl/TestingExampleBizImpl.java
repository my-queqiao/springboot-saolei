package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biz.TestingExampleBiz;
import com.example.mappers.TestingExampleMapper;
import com.example.pojo.TestingExample;

@Service
public class TestingExampleBizImpl implements TestingExampleBiz{
	@Autowired
	private TestingExampleMapper testingExampleMapper;
	@Override
	public List<TestingExample> page(Integer pageNumber, Integer pageSize, Integer search) {
		// SELECT * FROM table LIMIT 5,10;  // 检索记录行 6-15
		int limit = 0;
		if(pageNumber.intValue() == 1) {
			limit = 0;
		}else {
			limit = (pageNumber-1)*pageSize;
		}
		List<TestingExample> list = testingExampleMapper.page(search,limit,pageSize);
		return list;
	}

	@Override
	public Integer findTotal(Integer search) {
		int total = testingExampleMapper.findTotal(search);
		return total;
	}

}
