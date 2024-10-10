package com.goodsmarket.jh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.common.MD5HashingEncoder;
import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public int addUser(String loginId, String password, String name, String phone, String email, String nickName)
	{
		// 암호화
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, phone, email, nickName);
	}
	
	public User getUser(String loginId, String password)
	{
		String encryptPassword = MD5HashingEncoder.encode(password);
		User user = userRepository.selectUser(loginId, encryptPassword);
		
		return user;
	}
	
	// id 중복확인 Service
	public int checkUserId(String loginId)
	{
		return userRepository.countUserId(loginId);
	}
	
	// 프로필 이미지 가져오기 Service
	public User getProfileImage(int userId)
	{
		return userRepository.selectProfileImage(userId);
	}
}
