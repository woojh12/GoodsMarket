package com.goodsmarket.jh.used_trade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.used_trade.service.UsedTradeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usedtrade")
public class UsedTradeRestController {
	private UsedTradeService usedTradeService;
	
	@Autowired
	public UsedTradeRestController(UsedTradeService usedTradeService)
	{
		this.usedTradeService = usedTradeService;
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
		
		int count = usedTradeService.addUsedTrade(userId, title, contents, imagePath, location, sellerName, sellPrice);
		
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
	
	// 조회수 API
	@PostMapping("/increase/views")
	public Map<String, String> increaseViews(@RequestParam("id") int id)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		boolean check = usedTradeService.countUpUsedTradeViews(id);
		
		if(check == true)
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
