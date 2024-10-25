package com.goodsmarket.jh.used_trade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.used_trade.domain.FileImage;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;

@Service
public class FileImageService {
	@Autowired
	private FileImageRepository fileImageRepository;
	
	public FileImageService(FileImageRepository fileImageRepository)
	{
		this.fileImageRepository = fileImageRepository;
	}
	
	// 각 게시글의 파일 이미지를 불러오는 Service
	public List<FileImage> getFileImages(int usedTradeId)
	{
		return fileImageRepository.selectAllFileImage(usedTradeId);
	}
}
