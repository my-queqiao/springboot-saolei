package com.example.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biz.LiuyanBiz;
import com.example.mappers.LiuyanMapper;
import com.example.pojo.Liuyan;
import com.example.pojo.Users;

@Service
@Transactional
public class LiuyanBizImpl implements LiuyanBiz{
	@Autowired
	private LiuyanMapper liuyanMapper;
	
	//@CacheEvict({"demo"}) // 
	//@CacheEvict(value="demo",key="") // 清空demo策略下的，某个key值
	//@CacheEvict(value="demo",allEntries=true) // 清空以demo策略，缓存的所有数据
	@Override
	public void save(Liuyan ly) {
		liuyanMapper.insert(ly);
	}

	/* 将该方法的查询结果放到缓存中（该方法再次被调用时，首先会到ehcache中查找）
	 * 缓存中的数据用key-value的形式存储。key默认为入参参数值。如果将默认值写出来，写法是key="#入参"，比如：key="#name"
	 */
	//@Cacheable(value="demo",key="#入参") 
	//@Cacheable(value="demo") 
	@Override
	public List<Liuyan> getAll() {
		List<Liuyan> list = liuyanMapper.getAll();
		return list;
	}
}
