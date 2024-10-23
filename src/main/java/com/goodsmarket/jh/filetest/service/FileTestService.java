package com.goodsmarket.jh.filetest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.filetest.repository.FileTestRepository;

@Service
public class FileTestService {
	@Autowired
	private FileTestRepository fileTestRepository;
	
	public int addFile(MultipartFile file)
	{
		String filepath = FileManager.saveFile(1, file);
		return fileTestRepository.insertFile(filepath);
	}
}
