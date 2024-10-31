package com.goodsmarket.jh.used_trade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.used_trade.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comments")
public class CommentRestController {
		private CommentService commentService;
		
		@Autowired
		public CommentRestController(CommentService commentService)
		{
			this.commentService = commentService;
		}
	
		// 댓글 추가 API
		@PostMapping("/add")
		public Map<String, String> add(@RequestParam("usedTradeId") int usedTradeId
				, @RequestParam("contents") String contents
				, HttpSession session)
		{
			Map<String, String> resultMap = new HashMap<>();
			
			int userId = (Integer)session.getAttribute("userId");
			String userName = (String) session.getAttribute("userName");
			int count = commentService.addComment(usedTradeId, userId, userName, contents);
			
			if(count == 1)
			{
				resultMap.put("result", "success");
			}
			else
			{
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		}
		
		// 작성한 댓글 삭제 API
		@DeleteMapping("/delete")
		public Map<String, String> delete(@RequestParam("id") int id)
		{
			Map<String, String> resultMap = new HashMap<>();
			
			int count = commentService.removeCommentByUId(id);
			
			if(count == 1)
			{
				resultMap.put("result", "success");
			}
			else
			{
				resultMap.put("result", "fail");
			}
			
			return resultMap;
		}
}
