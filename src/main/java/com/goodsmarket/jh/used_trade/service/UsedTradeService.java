package com.goodsmarket.jh.used_trade.service;

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
}
