package com.goodsmarket.jh.used_trade.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.used_trade.domain.Comment;

@Mapper
public interface CommentRepository {
	// 사용자가 작성한 댓글을 저장하는 Repository
	public int insertComment(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId
			, @Param("nickName") String nickName
			, @Param("contents") String contents);
	
	// 게시글 별로 댓글 출력하는 Repository
	public List<Comment> selectAllCommentsByUsedTradeId(@Param("usedTradeId") int usedTradeId);
}
