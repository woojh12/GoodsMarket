package com.goodsmarket.jh.favorite.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.favorite.domain.Favorite;

@Mapper
public interface FavoriteRepository {
	public int insertFavorite(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	public int countFavorite(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	// 즐겨찾기 한개만 삭제 Repository
	public int deleteFavorite(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId);
	
	// 즐겨찾기한 것 모두 삭제
	public int deleteAllFavorite(@Param("userId") int userId);
	
	public int countAllFavorite(@Param("usedTradeId") int usedTradeId);
	
	// 즐겨찾기에 담긴 게시글 정보 가져오기 repository
	public int[] selectUserFavoriteList(@Param("userId") int userId);
}
