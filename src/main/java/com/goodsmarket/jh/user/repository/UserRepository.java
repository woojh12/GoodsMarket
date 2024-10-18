package com.goodsmarket.jh.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.user.domain.User;

@Mapper
public interface UserRepository {
	public int insertUser(@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("phone") String phone
			, @Param("email") String email
			, @Param("nickName") String nickName);
	
	public User selectUser(@Param("loginId") String loginId
			, @Param("password") String password);
	
	public int countUserId(@Param("loginId") String loginId);
	
	public User selectProfileImage(@Param("id") int id);
	
	// 회원 탈퇴 Repository
	public int deleteUser(@Param("id") int id);
}
