package com.example.mappers;

import java.util.List;

import com.example.pojo.Liuyan;

public interface LiuyanMapper {
    int insert(Liuyan record);
    List<Liuyan> getAll();
}