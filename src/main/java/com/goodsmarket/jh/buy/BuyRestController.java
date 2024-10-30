package com.goodsmarket.jh.buy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.buy.service.BuyService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/buy")
public class BuyRestController {
	private BuyService buyService;
	
	@Autowired
	public BuyRestController(BuyService buyService)
	{
		this.buyService = buyService;
	}
	
	// 구매한 물품 추가 API
	@PostMapping("/add")
	public Map<String, String> add(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count = buyService.addUsedTrade(usedTradeId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
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
	
	// 구매한 물품 해제 API
	@DeleteMapping("/delete")
	public Map<String, String> delete(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count = buyService.removeBuy(usedTradeId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
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
