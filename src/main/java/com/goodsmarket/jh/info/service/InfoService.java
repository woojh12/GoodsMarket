package com.goodsmarket.jh.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.common.MD5HashingEncoder;
import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.info.repository.InfoRepository;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;
import com.goodsmarket.jh.user.repository.UserRepository;

@Service
public class InfoService {
	private InfoRepository infoRepository;
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	private UserRepository userRepository;
	
	@Autowired
	public InfoService(InfoRepository infoRepository, UsedTradeService usedTradeService, FavoriteService  favoriteService, UserRepository userRepository)
	{
		this.infoRepository = infoRepository;
		this.usedTradeService = usedTradeService;
		this. favoriteService =  favoriteService;
		this.userRepository = userRepository;
	}
	
	// 회원정보 수정 Service
	public int setUserInfo(int userId
			, String password
			, String phone
			, String email
			, String nickName
			, MultipartFile file)
	{
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		String removeImage = userRepository.selectProfileImage(userId);
		FileManager.removeFile(removeImage);
		
		String imagePath = FileManager.saveFile(userId, file);
		
		return infoRepository.updateUserInfo(userId, encryptPassword, phone, email, nickName, imagePath);
	}
}
