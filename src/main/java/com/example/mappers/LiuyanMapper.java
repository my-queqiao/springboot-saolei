package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pojo.Liuyan;
//@Mapper
public interface LiuyanMapper {
    int insert(Liuyan record);
    List<Liuyan> getAll();
}