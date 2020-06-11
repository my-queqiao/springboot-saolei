package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.biz.ChangeCodeBiz;
import com.example.biz.TestingExampleBiz;
import com.example.mappers.ChangeCodeMapper;
import com.example.mappers.TestingExampleMapper;
import com.example.pojo.ChangeCode;
import com.example.pojo.TestingExample;

@Service
public class ChangeCodeBizImpl implements ChangeCodeBiz{
	@Autowired
	private ChangeCodeMapper changeCodeMapper;
	@Override
	public List<ChangeCode> page(Integer pageNumber, Integer pageSize, Integer search) {
		// SELECT * FROM table LIMIT 5,10;  // 检索记录行 6-15
		int limit = 0;
		if(pageNumber.intValue() == 1) {
			limit = 0;
		}else {
			limit = (pageNumber-1)*pageSize;
		}
		List<ChangeCode> list = changeCodeMapper.page(search,limit,pageSize);
		return list;
	}

	@Override
	public Integer findTotal(Integer search) {
		int total = changeCodeMapper.findTotal(search);
		return total;
	}

	@Override
	public void insertBatch(List<ChangeCode> ccs) {
		changeCodeMapper.insertBatch(ccs);
	}

	@Override
	public void deleteByGitUrlAndBranchs(String gitUrlAndBranchs) {
		changeCodeMapper.deleteByGitUrlAndBranchs(gitUrlAndBranchs);
	}

}
