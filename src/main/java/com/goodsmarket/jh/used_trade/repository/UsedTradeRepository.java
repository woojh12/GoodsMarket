package com.goodsmarket.jh.used_trade.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.used_trade.domain.UsedTrade;

@Mapper
public interface UsedTradeRepository {
	public int insertUsedTrade(@Param("userId") int userId
			, @Param("title") String title
			, @Param("contents") String contents
			, @Param("imagePath") String imagePath
			, @Param("location") String location
			, @Param("addTradingPlace") String addTradingPlace
			, @Param("sellerName") String sellerName
			, @Param("sellPrice") int sellPrice);
	
	public List<UsedTrade> selectAllUsedTrade();
	
	public UsedTrade selectUsedTrade(@Param("id") int id);
	
	public int updateUsedTradeViews(@Param("id") int id);
}
