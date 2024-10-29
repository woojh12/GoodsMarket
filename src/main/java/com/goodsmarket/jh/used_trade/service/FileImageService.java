package com.goodsmarket.jh.used_trade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
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
	
	// 사용자가 작성한 게시글의 업로드한 파일 삭제 Service
	public int removeFileImagesByUsedTradeId(int usedTradeId)
	{
		return fileImageRepository.deleteFileImageByUsedTradeId(usedTradeId);
	}
	
	// 사용자가 업로드한 모든 파일 삭제 Service
	public int removeFileImagesByUserId(int userId)
	{
		return fileImageRepository.deleteAllFileImageByUserId(userId);
	}
	
	// 사용자가 업로드한 모든 파일 출력 Service
	public List<FileImage> getAllFileImagesByUserId(int userId)
	{
		return fileImageRepository.selectAllFileImageByUserId(userId);
	}
}
