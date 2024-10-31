package com.goodsmarket.jh.sell.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.sell.domain.Sell;

@Mapper
public interface SellRepository {
	// 사용자가 판매한 내역을 저장하는 Repository
	public int insertSell(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	// 해당 게시글이 판매완료된 게시글인지 확인하는 Repository
	public Sell selectSellByUsedTradeId(@Param("usedTradeId") int usedTradeId);
	
	// 사용자가 판매한 물품 정보를 출력하는 Repository
	public List<Sell> selectAllSellByUserId(@Param("userId") int userId);
}
