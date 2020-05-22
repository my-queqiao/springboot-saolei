package com.example.mappers;

import com.example.pojo.TestingReleaseAddCode;

public interface TestingReleaseAddCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestingReleaseAddCode record);

    int insertSelective(TestingReleaseAddCode record);

    TestingReleaseAddCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestingReleaseAddCode record);

    int updateByPrimaryKey(TestingReleaseAddCode record);
}