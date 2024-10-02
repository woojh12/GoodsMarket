package com.goodsmarket.jh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return userRepository.insertUser(loginId, password, name, phone, email, nickName);
	}
	
	public User getUser(String loginId, String password)
	{
		User user = userRepository.selectUser(loginId, password);
		
		return user;
	}
}
