package com.goodsmarket.jh.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodsmarket.jh.shoppingcart.domain.ShoppingCart;
import com.goodsmarket.jh.shoppingcart.service.ShoppingCartService;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;
import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my/info")
public class InfoController {
	private UserService userService;
	private UsedTradeService usedTradeService;
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public InfoController(UserService userService, UsedTradeService usedTradeService, ShoppingCartService shoppingCartService)
	{
		this.userService = userService;
		this.usedTradeService = usedTradeService;
		this.shoppingCartService = shoppingCartService;
	}
	
	@GetMapping("/view")
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
	
	@GetMapping("/shoppingcart-view")
	public String infoShoppingCart(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		// 전체 장바구니 목록 가져오기
		List<ShoppingCart> shoppingCartList = shoppingCartService.getUserShopingCartList(userId);
		List<UsedTrade> usedTradeList = new ArrayList<UsedTrade>();

				
		for(ShoppingCart shoppingCart:shoppingCartList)
		{
			int usedTradeId = shoppingCart.getUsedTradeId();			
					
		}
		model.addAttribute("goodsPostList", usedTradeList);	
		
		return "/info/shoppingcart";
	}
}
