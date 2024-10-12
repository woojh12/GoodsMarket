package com.goodsmarket.jh.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.shoppingcart.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/shoppingcart")
@RestController
public class ShoppingCartRestController {
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingCartRestController(ShoppingCartService shoppingCartService)
	{
		this.shoppingCartService = shoppingCartService;
	}
	
	// 장바구니 담기 API
	@PostMapping("/add")
	public Map<String, String> addShoppingCart(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		int count = shoppingCartService.addShoppingCart(usedTradeId, userId);
		
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
	
	// 장바구니 체크 API
	@PostMapping("/check/shoppingcart")
	public Map<String, String> checkShoppingCart(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		int count = shoppingCartService.getShoppingCartCount(usedTradeId, userId);
		
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
	
	// 장바구니 삭제 API
	@DeleteMapping("/delete/shoppingcart")
	public Map<String, String> deleteShoppingCart(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count = shoppingCartService.removeShoppingCartCount(usedTradeId, userId);
		
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
