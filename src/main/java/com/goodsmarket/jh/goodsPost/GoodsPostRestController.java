package com.goodsmarket.jh.goodsPost;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.goodsPost.service.GoodsPostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/goodsPost")
public class GoodsPostRestController {
	private GoodsPostService goodsPostService;
	
	@Autowired
	public GoodsPostRestController(GoodsPostService goodsPostService)
	{
		this.goodsPostService = goodsPostService;
	}
	
	// 판매 작성글 API
	@PostMapping("/create")
	public Map<String, String> sellCreate(@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value="imagePath", required=false) MultipartFile imagePath
			, @RequestParam("location") String location
			, @RequestParam("sellPrice") int sellPrice
			, HttpSession session)
	{
		Map<String, String> resultMap = new HashMap<>();
	
		// 판매 작성하는 사용자의 세션 정보 받아오기
		int userId = (Integer)session.getAttribute("userId");
		String sellerName = (String)session.getAttribute("userName");
		
		int count = goodsPostService.addUsedTrade(userId, title, contents, imagePath, location, sellerName, sellPrice);
		
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
