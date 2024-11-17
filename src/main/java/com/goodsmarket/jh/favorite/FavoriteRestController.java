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

@RequestMapping("/favorite")
@RestController
public class FavoriteRestController {
	private FavoriteService favoriteService;
	
	@Autowired
	public FavoriteRestController(FavoriteService  favoriteService)
	{
		this. favoriteService =  favoriteService;
	}
	
	// 즐겨찾기 추가 API
	@PostMapping("/add")
	public Map<String, String> addFavorite(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		int count =  favoriteService.addFavorite(usedTradeId, userId);
		
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
	
	// 즐겨찾기 체크 API
	@PostMapping("/check")
	public Map<String, String> checkFavorite(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		int count =  favoriteService.getFavoriteCount(usedTradeId, userId);
		
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
	
	// 즐겨찾기 삭제 API
	@DeleteMapping("/delete")
	public Map<String, String> deleteFavorite(@RequestParam("usedTradeId") int usedTradeId
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		int count =  favoriteService.removeFavoriteCount(usedTradeId, userId);
		
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
