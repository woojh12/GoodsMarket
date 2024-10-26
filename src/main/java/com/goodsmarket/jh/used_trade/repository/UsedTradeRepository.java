package com.goodsmarket.jh.used_trade.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.used_trade.domain.UsedTrade;

@Mapper
public interface UsedTradeRepository {
	public int insertUsedTrade(UsedTrade usedTrade);
	
	public List<UsedTrade> selectAllUsedTrade();
	
	// 물품 검색 기능에 사용될 Repository
	public List<UsedTrade> selectAllUsedTradeByTitle(@Param("title") String title);
	
	public UsedTrade selectUsedTrade(@Param("id") int id);
	
	// 조회수 증가 Repository
	public int updateUsedTradeViews(@Param("id") int id);
	
	// 모든 게시글 삭제 Repository
	public int deleteAllUsedTrade(@Param("userId") int userId);
	
	// 게시글 1개 삭제 Repository
	public int deleteUsedTradeById(@Param("id") int id);
	
	// 게시글 수정 Repository
	public int updateUsedTrade(@Param("id") int id
			, @Param("title") String title
			, @Param("contents") String contents
			, @Param("imagePath") String imagePath
			, @Param("place") String place
			, @Param("addTradingPlace") String addTradingPlace
			, @Param("sellPrice") int sellPrice);
	
	// 사용자가 작성한 게시글 모두 조회 Repository(사용자 PK로 판단)
	public List<UsedTrade> selectUsedTradeByUserId(@Param("userId") int userId);
}
