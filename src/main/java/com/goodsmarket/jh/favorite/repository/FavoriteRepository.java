package com.goodsmarket.jh.favorite.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.favorite.domain.Favorite;

@Mapper
public interface FavoriteRepository {
	public int insertShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int deleteShoppingCart(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countAllShoppingCart(@Param("usedTradeId") int usedTradeId);
	
	// 장바구니에 담긴 게시글 정보 가져오기 repository
	public int[] selectUserShopingCartList(@Param("userId") int userId);
}
