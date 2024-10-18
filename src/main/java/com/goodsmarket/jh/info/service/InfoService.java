package com.goodsmarket.jh.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.common.MD5HashingEncoder;
import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.info.repository.InfoRepository;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;

@Service
public class InfoService {
	private InfoRepository infoRepository;
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	
	@Autowired
	public InfoService(InfoRepository infoRepository, UsedTradeService usedTradeService, FavoriteService  favoriteService)
	{
		this.infoRepository = infoRepository;
		this.usedTradeService = usedTradeService;
		this. favoriteService =  favoriteService;
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
		String imagePath = FileManager.saveFile(userId, file);
		
		return infoRepository.updateUserInfo(userId, encryptPassword, phone, email, nickName, imagePath);
	}
}
