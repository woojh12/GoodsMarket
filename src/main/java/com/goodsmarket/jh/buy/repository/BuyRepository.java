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
	
	
}
