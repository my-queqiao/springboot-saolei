package com.example.mappers;

import java.util.List;

import com.example.pojo.TestingExample;

public interface TestingExampleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestingExample record);

    int insertSelective(TestingExample record);

    TestingExample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestingExample record);

    int updateByPrimaryKey(TestingExample record);

	List<TestingExample> page(Integer search, int limit, Integer pageSize);

	int findTotal(Integer search);
}