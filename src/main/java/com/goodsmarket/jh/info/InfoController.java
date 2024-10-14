package com.goodsmarket.jh.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.info.service.InfoService;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;
import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/info")
public class InfoController {
	private UserService userService;
	private UsedTradeService usedTradeService;
	private InfoService infoService;
	private FavoriteService favoriteService;
	
	@Autowired
	public InfoController(UserService userService, UsedTradeService usedTradeService
			, InfoService infoService, FavoriteService favoriteService)
	{
		this.userService = userService;
		this.usedTradeService = usedTradeService;
		this.infoService = infoService;
		this. favoriteService = favoriteService;
	}
	
	@GetMapping("/my-view")
	public String info(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		User user = userService.getProfileImage(userId);
		
		model.addAttribute("user", user);
		
		return "/info/myinfo";
	}
	
	@GetMapping("/update-view")
	public String infoUpdate()
	{
		return "/info/myinfoUpdate";
	}
	
	@GetMapping("/postList-view")
	public String infoPostList()
	{
		return "/info/postList";
	}
	
	@GetMapping("/favorite-view")
	public String infoShoppingCart(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		List<UsedTrade> usedTradeList =  favoriteService.getUserShopingCartList(userId);
		
		model.addAttribute("usedTradeList", usedTradeList);
		return "/info/favorite";
	}
}
