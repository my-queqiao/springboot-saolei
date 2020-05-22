package com.example.biz;

import java.util.List;
import com.example.pojo.TestingExample;

public interface TestingExampleBiz {

	List<TestingExample> page(Integer pageNumber, Integer pageSize, Integer search);

	Integer findTotal(Integer search);
}
