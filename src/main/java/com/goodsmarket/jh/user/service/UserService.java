package com.goodsmarket.jh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.common.MD5HashingEncoder;
import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;
import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	
	@Autowired
	public UserService(UserRepository userRepository, UsedTradeService usedTradeService ,FavoriteService favoriteService)
	{
		this.userRepository = userRepository;
		this.usedTradeService = usedTradeService;
		this.favoriteService = favoriteService;
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
	
	// 회원정보 탈퇴 Service	--- 작성한 게시글 및 즐겨찾기도 삭제
	public int removeUser(int id)
	{
		favoriteService.removeAllShoppingCart(id);
		usedTradeService.removeAllUsedTrade(id);
		
		return userRepository.deleteUser(id);
	}
}
