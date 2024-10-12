package com.goodsmarket.jh.shoppingcart.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShoppingCartRepository {
	public int insertShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int deleteShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countAllShoppingCart(@Param("usedTradeId") int usedTradeId);
}
