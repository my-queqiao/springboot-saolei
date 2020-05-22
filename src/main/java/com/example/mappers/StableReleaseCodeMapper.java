package com.example.mappers;

import java.util.List;

import com.example.pojo.StableReleaseCode;

public interface StableReleaseCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StableReleaseCode record);

    int insertSelective(StableReleaseCode record);

    StableReleaseCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StableReleaseCode record);

    int updateByPrimaryKey(StableReleaseCode record);

	List<StableReleaseCode> findByTestingExampleId(Integer id);
}