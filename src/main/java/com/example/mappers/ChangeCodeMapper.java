package com.example.mappers;

import com.example.pojo.ChangeCode;

public interface ChangeCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeCode record);

    int insertSelective(ChangeCode record);

    ChangeCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeCode record);

    int updateByPrimaryKey(ChangeCode record);
}