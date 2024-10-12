package com.goodsmarket.jh.goodsPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodsmarket.jh.goodsPost.domain.GoodsPost;
import com.goodsmarket.jh.goodsPost.service.GoodsPostService;
import com.goodsmarket.jh.shoppingcart.service.ShoppingCartService;

@RequestMapping("/goodsPost")
@Controller
public class GoodsPostController {
	private GoodsPostService goodsPostService;
	private ShoppingCartService shoppingCartService;
	
	public GoodsPostController(GoodsPostService goodsPostService, ShoppingCartService shoppingCartService)
	{
		this.goodsPostService = goodsPostService;
		this.shoppingCartService = shoppingCartService;
	}
	
	@GetMapping("/list-view")
	public String goodsList(Model model)
	{
		List<GoodsPost> goodsPostList = new ArrayList<>();
		
		goodsPostList = goodsPostService.getAllUsedTrade();
		
		model.addAttribute("goodsPostList", goodsPostList);
		
		return "post/goodsList";
	}
	
	@GetMapping("/create-view")
	public String goodsCreate()
	{
		return "post/goodsInput";
	}
	
	@GetMapping("/show-view")
	public String goodsShow(Model model
			, @RequestParam("id") int id)
	{		
		GoodsPost goodsPost = goodsPostService.getUsedTrade(id);
		model.addAttribute("goodsPost", goodsPost);
	
		// 장바구니 버튼 누른 사용자 수 조회
		List<GoodsPost> goodsPostList = goodsPostService.getAllUsedTrade();
		for(GoodsPost sellPost:goodsPostList)
		{
			int shoppingCartCount = shoppingCartService.getAllShoppingCartCount(sellPost.getId());			
			model.addAttribute("shoppingCartCount", shoppingCartCount);
		}
		
		return "post/goodsPost";
	}
}
