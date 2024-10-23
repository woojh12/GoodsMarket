package com.goodsmarket.jh.used_trade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;

@RequestMapping("/usedtrade")
@Controller
public class UsedTradeController {
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	
	public UsedTradeController(UsedTradeService usedTradeService, FavoriteService favoriteService)
	{
		this.usedTradeService = usedTradeService;
		this. favoriteService =  favoriteService;
	}
	
	@GetMapping("/list-view")
	public String goodsList(Model model)
	{
		List<UsedTrade> usedTradeList = usedTradeService.getAllUsedTrade();
	
		model.addAttribute("usedTradeList", usedTradeList);
		
		return "usedtrade/postList";
	}
	
	@GetMapping("/create-view")
	public String goodsCreate()
	{
		return "usedtrade/input";
	}
	
	@GetMapping("/show-view")
	public String goodsShow(Model model
			, @RequestParam("id") int id)
	{		
		UsedTrade usedTrade = usedTradeService.getUsedTrade(id);
		model.addAttribute("usedTrade", usedTrade);
	
		// 장바구니 버튼 누른 사용자 수 조회
		List<UsedTrade> usedTradeList = usedTradeService.getAllUsedTrade();
		
		// 선택한 게시글의 찜 수 조회
		int shoppingCartCount =  favoriteService.getAllShoppingCartCount(id);
		
		model.addAttribute("shoppingCartCount", shoppingCartCount);
		
		return "usedtrade/detail";
	}
	
	@GetMapping("/update-view")
	public String goodsUpdate(@RequestParam("id") int id
			, Model model)
	{
		UsedTrade usedTrade = usedTradeService.getUsedTrade(id);
		model.addAttribute("usedTrade", usedTrade);
		
		return "/usedTrade/update";
	}
	
	@GetMapping("/search-view")
	public String search(@RequestParam("title") String title
			, Model model)
	{
		List<UsedTrade> usedTradeList = usedTradeService.getAllUsedTradeByTitle(title);
		
		model.addAttribute("usedTradeList", usedTradeList);
		
		return "/usedTrade/search";
	}
}
