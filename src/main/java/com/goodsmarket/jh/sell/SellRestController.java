package com.goodsmarket.jh.sell;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.sell.service.SellService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/sell")
public class SellRestController {
	private SellService sellService;
	
	@Autowired
	public SellRestController(SellService sellService)
	{
		this.sellService = sellService;
	}
	
	// 사용자가 판매한 내역을 저장하는 api
	@PostMapping("/add")
	public Map<String, String> add(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count = sellService.addSell(usedTradeId, userId);
		
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
