package com.example.mappers;

import java.util.List;

import com.example.pojo.ChangeCode;

public interface ChangeCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeCode record);

    int insertSelective(ChangeCode record);

    ChangeCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeCode record);

    int updateByPrimaryKey(ChangeCode record);

	int findTotal(Integer search);

	void insertBatch(List<ChangeCode> ccs);

	List<ChangeCode> page(Integer search, int limit, Integer pageSize);

	void deleteByGitUrlAndBranchs(String gitUrlAndBranchs);
}