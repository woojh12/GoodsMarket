package com.goodsmarket.jh.used_trade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;

@Service
public class FileImageService {
	@Autowired
	private FileImageRepository fileImageRepository;
	
	public FileImageService(FileImageRepository fileImageRepository)
	{
		this.fileImageRepository = fileImageRepository;
	}
	
	// 파일 이미지 업로드 Service ---> 불필요해서 삭제예정
	/*
	public int addFileImage(int usedTradeId, int userId, MultipartFile[] files)
	{
		int count = 0;
		for(int i = 0; i < files.length; i++)
		{
			String imagePath = FileManager.saveFile(userId, files[i]);			 
			count = fileImageRepository.insertFileImage(usedTradeId, userId, imagePath);
		}
		
		return count;
	}
	*/
}
