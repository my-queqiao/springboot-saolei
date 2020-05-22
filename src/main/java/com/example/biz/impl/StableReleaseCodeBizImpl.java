package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biz.StableReleaseCodeBiz;
import com.example.mappers.StableReleaseCodeMapper;
import com.example.pojo.StableReleaseCode;

@Service
public class StableReleaseCodeBizImpl implements StableReleaseCodeBiz{
	@Autowired
	private StableReleaseCodeMapper stableReleaseCodeMapper;

	@Override
	public List<StableReleaseCode> getAll(Integer pageNumber, Integer pageSize, Integer search) {
		return null;
	}

	@Override
	public Integer findTotal(Integer search) {
		return null;
	}

	@Override
	public List<StableReleaseCode> findByTestingExampleId(Integer id) {
		List<StableReleaseCode> list = stableReleaseCodeMapper.findByTestingExampleId(id);
		return list;
	}
	
	
}
