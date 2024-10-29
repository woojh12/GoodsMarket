package com.goodsmarket.jh.buy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.buy.domain.Buy;
import com.goodsmarket.jh.buy.repository.BuyRepository;

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
		return buyRepository.insertUsedTrade(usedTradeId, usedTradeId);
	}
	
	// 사용자가 구매한 전체 물품을 출력하는 Service			---> 마이페이지 view에서 사용
	public List<Buy> getAllBuyByUserId(int userId)
	{
		return buyRepository.selectAllBuyByUserId(userId);
	}
}
