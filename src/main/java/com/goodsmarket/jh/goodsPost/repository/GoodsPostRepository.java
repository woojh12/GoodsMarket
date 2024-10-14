package com.goodsmarket.jh.goodsPost.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.goodsPost.domain.GoodsPost;

@Mapper
public interface GoodsPostRepository {
	public int insertUsedTrade(@Param("userId") int userId
			, @Param("title") String title
			, @Param("contents") String contents
			, @Param("imagePath") String imagePath
			, @Param("location") String location
			, @Param("sellerName") String sellerName
			, @Param("sellPrice") int sellPrice);
	
	public List<GoodsPost> selectAllUsedTrade();
	
	public GoodsPost selectUsedTrade(@Param("id") int id);
	
	public int updateUsedTradeViews(@Param("id") int id);
	
	// 장바구니 저장 목록 불러오기 Repository
	public List<GoodsPost> selectAllShoppingCartList(@Param("id") int id);
}
