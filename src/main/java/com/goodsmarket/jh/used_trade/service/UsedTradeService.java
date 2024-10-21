package com.goodsmarket.jh.used_trade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.repository.UsedTradeRepository;

@Service
public class UsedTradeService {
	private UsedTradeRepository usedTradeRepository;
	
	@Autowired
	public UsedTradeService(UsedTradeRepository usedTradeRepository)
	{
		this.usedTradeRepository = usedTradeRepository;
	}
	
	// 판매 작성글 저장 Service
	public int addUsedTrade(int userid
			, String title
			, String contents
			, MultipartFile file
			, String location
			, String addTradingPlace
			, String sellerName
			, int sellPrice)
	{
		String imagePath = FileManager.saveFile(userid, file);
		
		return usedTradeRepository.insertUsedTrade(userid, title, contents, imagePath, location, addTradingPlace, sellerName, sellPrice);
	}
	
	// 전체 판매목록 불러오기 Service
	public List<UsedTrade> getAllUsedTrade()
	{
		return usedTradeRepository.selectAllUsedTrade();
	}
	
	// 선택된 판매글 불러오기 Service
	public UsedTrade getUsedTrade(int id)
	{
		return usedTradeRepository.selectUsedTrade(id);
	}
	
	// 게시글 조회수 Service
	public boolean countUpUsedTradeViews(int id)
	{
		int count = usedTradeRepository.updateUsedTradeViews(id);
		
		if(count >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// 사용자가 작성한 게시글 정보 조회 Service
	public List<UsedTrade> getUsedTradeByUserId(int userId)
	{
		return usedTradeRepository.selectUsedTradeByUserId(userId);
	}
	
	// 사용자가 작성한 모든 게시글 삭제 Service
	public int removeAllUsedTrade(int userId)
	{
		String imagePath = "";
		List<String> imagePaths = new ArrayList<>();
		
		// 게시물 저장 개수 추출 : 이미지 추출 반복에서 사용
		int usedTradeCount = usedTradeRepository.countUsedTradeByUserId(userId);
		
		// 사용자가 작성한 게시물의 이미지 추출
		for(int i = 0; i < usedTradeCount; i++)
		{
			//imagePath = usedTradeRepository.selectUsedTradeByUserId(userId).getImagePath();
			//imagePaths.add(imagePath);
			if(imagePath != null)
			{
				FileManager.removeFile(imagePath);				
			}
		}
		
		int count = usedTradeRepository.deleteAllUsedTrade(userId);
		
		return count;
	}
	
	// 사용자가 작성한 모든 게시물의 개수 조회 Service
	public int getUsedTradeCountByUserId(int userId)
	{
		return usedTradeRepository.countUsedTradeByUserId(userId);
	}
}
