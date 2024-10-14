package com.goodsmarket.jh.shoppingcart.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.shoppingcart.domain.ShoppingCart;

@Mapper
public interface ShoppingCartRepository {
	public int insertShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int deleteShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countAllShoppingCart(@Param("usedTradeId") int usedTradeId);
	
	// 장바구니에 담긴 게시글 정보 가져오기 repository
	public List<ShoppingCart> selectUserShopingCartList(@Param("userId") int userId);
}
