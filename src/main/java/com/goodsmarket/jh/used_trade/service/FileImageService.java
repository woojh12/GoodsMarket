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
	
	// 파일 이미지 업로드 Service
	public int addFileImage(int usedTradeId, int userId, MultipartFile file)
	{
		String imagePath = FileManager.saveFile(userId, file);
		
		return fileImageRepository.insertFileImage(usedTradeId, userId, imagePath);
	}
}
