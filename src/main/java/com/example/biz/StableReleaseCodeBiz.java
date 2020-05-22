package com.example.biz;

import java.util.List;

import com.example.pojo.StableReleaseCode;

public interface StableReleaseCodeBiz {

	List<StableReleaseCode> getAll(Integer pageNumber, Integer pageSize, Integer search);

	Integer findTotal(Integer search);

	List<StableReleaseCode> findByTestingExampleId(Integer id);
}
