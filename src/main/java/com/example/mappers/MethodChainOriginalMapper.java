package com.example.mappers;

import com.example.pojo.MethodChainOriginal;

public interface MethodChainOriginalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MethodChainOriginal record);

    int insertSelective(MethodChainOriginal record);

    MethodChainOriginal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MethodChainOriginal record);

    int updateByPrimaryKey(MethodChainOriginal record);
}