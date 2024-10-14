package com.goodsmarket.jh.favorite;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.favorite.service.FavoriteService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/shoppingcart")
@RestController
public class FavoriteRestController {
	private FavoriteService favoriteService;
	
	@Autowired
	public FavoriteRestController(FavoriteService  favoriteService)
	{
		this. favoriteService =  favoriteService;
	}
	
	// 장바구니 담기 API
	@PostMapping("/add")
	public Map<String, String> addShoppingCart(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		int count =  favoriteService.addShoppingCart(usedTradeId, userId);
		
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
	
		int count =  favoriteService.getShoppingCartCount(usedTradeId, userId);
		
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
	@DeleteMapping("/delete")
	public Map<String, String> deleteShoppingCart(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count =  favoriteService.removeShoppingCartCount(usedTradeId, userId);
		
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
