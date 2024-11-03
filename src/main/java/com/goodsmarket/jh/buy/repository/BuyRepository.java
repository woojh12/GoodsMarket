package com.goodsmarket.jh.buy.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.buy.domain.Buy;

@Mapper
public interface BuyRepository {
	// 구매한 물품을 추가하는 Repository
	public int insertUsedTrade(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	// 사용자가 구매한 전체 물품을 출력하는 Repository
	public List<Buy> selectAllBuyByUserId(@Param("userId") int userId);
	
	// 물품 구매한 사용자 정보 확인 Repository
	public Buy selectBuyByUsedTradeId(@Param("usedTradeId") int usedTradeId);
	
	// 구매 취소하는 Repository
	public int deleteBuy(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	// 구매한 물품의 개수를 출력하는 Repository
	public int countAllByUserId(@Param("userId") int userId);
}
