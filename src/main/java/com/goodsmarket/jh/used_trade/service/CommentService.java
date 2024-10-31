package com.goodsmarket.jh.used_trade.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.used_trade.domain.Comment;
import com.goodsmarket.jh.used_trade.repository.CommentRepository;

@Service
public class CommentService {
	private CommentRepository commentRepository;
	
	@Autowired
	public CommentService(CommentRepository commentRepository)
	{
		this.commentRepository = commentRepository;
	}
	
	// 사용자가 작성한 댓글을 저장하는 Service
	public int addComment(int usedTradeId, int userId, String nickName, String contents)
	{
		return commentRepository.insertComment(usedTradeId, userId, nickName, contents); 
	}
	
	// 게시글 별로 댓글 출력하는 Service
	public List<Comment> getAllCommentsByUsedTradeId(int usedTradeId)
	{
		return commentRepository.selectAllCommentsByUsedTradeId(usedTradeId);
	}
	
	// 작성한 댓글을 삭제하는 Service
	public int removeCommentByUId(int id)
	{
		return commentRepository.deleteCommentById(id);
	}
	
	// 사용자가 작성한 모든 댓글 삭제 Repository
	public int removeAllCommentsByUserId(@Param("userId") int userId)
	{
		return commentRepository.deleteAllCommentsByUserId(userId);
	}
}
