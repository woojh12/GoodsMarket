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

@RequestMapping("/goodsPost")
@Controller
public class GoodsPostController {
	private GoodsPostService goodsPostService;
	
	public GoodsPostController(GoodsPostService goodsPostService)
	{
		this.goodsPostService = goodsPostService;
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
		
		return "post/goodsPost";
	}
}
