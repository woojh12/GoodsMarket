package com.goodsmarket.jh.buy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.buy.domain.Buy;
import com.goodsmarket.jh.buy.repository.BuyRepository;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;

@Service
public class BuyService {
	private BuyRepository buyRepository;

	@Autowired
	public BuyService(BuyRepository buyRepository)
	{
		this.buyRepository = buyRepository;
	}
	
	// 구매한 물품을 추가하는 Service
	public int addUsedTrade(int usedTradeId, int userId)
	{
		return buyRepository.insertUsedTrade(usedTradeId, userId);
	}
	
	// 사용자가 구매한 전체 물품을 출력하는 Service			---> 마이페이지 view에서 사용
	public List<Buy> getAllBuyByUserId(int userId)
	{
		return buyRepository.selectAllBuyByUserId(userId);
	}
	
	// 물품 구매한 사용자 정보 확인 Service	---> 상세 페이지에서 사용
	public Buy getBuyByUsedTradeId(int usedTradeId)
	{
		return buyRepository.selectBuyByUsedTradeId(usedTradeId);
	}
	
	// 구매 취소하는 Service
	public int removeBuy(int usedTradeId, int userId)
	{
		return buyRepository.deleteBuy(usedTradeId, userId);
	}
}
