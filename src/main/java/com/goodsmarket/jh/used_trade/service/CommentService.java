package com.goodsmarket.jh.used_trade.service;

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
}
