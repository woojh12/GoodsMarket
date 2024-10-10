package com.goodsmarket.jh.info.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InfoRepository {
	public int updateUserInfo(@Param("id") int id
			, @Param("password") String password
			, @Param("phone") String phone
			, @Param("email") String email
			, @Param("nickName") String nickName
			, @Param("imagePath") String imagePath);
}
