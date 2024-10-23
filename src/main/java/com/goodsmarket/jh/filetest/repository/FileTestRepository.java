package com.goodsmarket.jh.filetest.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileTestRepository {
	public int insertFile(@Param("filepath") String filepath);
}
