package com.goodsmarket.jh.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodsmarket.jh.buy.service.BuyService;
import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.info.service.InfoService;
import com.goodsmarket.jh.sell.service.SellService;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
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
	private SellService sellService;
	private BuyService buyService;
	
	@Autowired
	public InfoController(UserService userService, UsedTradeService usedTradeService, InfoService infoService
			, FavoriteService favoriteService, SellService sellService, BuyService buyService)
	{
		this.userService = userService;
		this.usedTradeService = usedTradeService;
		this.infoService = infoService;
		this. favoriteService = favoriteService;
		this.sellService = sellService;
		this.buyService = buyService;
	}
	
	@GetMapping("/my-view")
	public String info(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		// 전체 게시글의 개수
		int postCount = usedTradeService.getCountAllByUserId(userId);
		
		// 구매한 물품 개수 가져오기
		int buyCount = buyService.getCountAllByUserId(userId);
		
		// 판매한 물품 개수 가져오기
		int sellCount = sellService.getCountAllByUserId(userId);
		
		String userImagePath = userService.getProfileImage(userId);
		
		model.addAttribute("userImagePath", userImagePath);
		
		model.addAttribute("postCount", postCount);
		model.addAttribute("buyCount", buyCount);
		model.addAttribute("sellCount", sellCount);
		
		return "/info/myinfo";
	}
	
	@GetMapping("/update-view")
	public String infoUpdate(HttpSession session, Model model)
	{
		int userId = (Integer)session.getAttribute("userId");
		User user = userService.getUserById(userId);
		
		model.addAttribute("user", user);
		
		return "/info/myinfoUpdate";
	}
	
	@GetMapping("/postList-view")
	public String infoPostList(HttpSession session, Model model)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		List<BoardDTO> usedTradeList = usedTradeService.getUsedTradeByUserId(userId);
		
		model.addAttribute("usedTradeList", usedTradeList);
		
		return "/info/postList";
	}
	
	@GetMapping("/favorite-view")
	public String infoShoppingCart(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
	
		List<BoardDTO> usedTradeList =  favoriteService.getUserFavoriteList(userId);
		
		model.addAttribute("usedTradeList", usedTradeList);
		return "/info/favorite";
	}
	
	@GetMapping("/buy-view")
	public String buyList(Model model, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		List<BoardDTO> usedTradeList = buyService.getAllBuyByUserId(userId);
		
		model.addAttribute("usedTradeList", usedTradeList);
		
		return "/info/buyList";
	}
	
	@GetMapping("/sell-view")
	public String sellList(Model model, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		List<BoardDTO> usedTradeList = sellService.getAllSellByUserId(userId);
		
		model.addAttribute("usedTradeList", usedTradeList);
		
		return "/info/sellList";
	}
}
