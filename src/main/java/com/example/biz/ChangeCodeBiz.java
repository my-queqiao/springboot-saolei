package com.example.biz;

import java.util.List;

import com.example.pojo.ChangeCode;

public interface ChangeCodeBiz {

	List<ChangeCode> page(Integer pageNumber, Integer pageSize, Integer search);

	Integer findTotal(Integer search);

	void insertBatch(List<ChangeCode> ccs);

	void deleteByGitUrlAndBranchs(String gitUrlAndBranchs);
}
