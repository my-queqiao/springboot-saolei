package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biz.LiuyanBiz;
import com.example.mappers.LiuyanMapper;
import com.example.pojo.Liuyan;

@Service
@Transactional
public class LiuyanBizImpl implements LiuyanBiz{
	@Autowired
	private LiuyanMapper liuyanMapper;
	
	@Override
	public void save(Liuyan ly) {
		liuyanMapper.insert(ly);
	}

	@Override
	public List<Liuyan> getAll() {
		//List<Liuyan> list = liuyanMapper.selectByExample(null);
		return null;
	}

}
