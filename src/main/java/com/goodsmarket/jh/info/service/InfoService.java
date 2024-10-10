package com.goodsmarket.jh.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.common.MD5HashingEncoder;
import com.goodsmarket.jh.info.repository.InfoRepository;

@Service
public class InfoService {
	private InfoRepository infoRepository;
	
	@Autowired
	public InfoService(InfoRepository infoRepository)
	{
		this.infoRepository = infoRepository;
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
