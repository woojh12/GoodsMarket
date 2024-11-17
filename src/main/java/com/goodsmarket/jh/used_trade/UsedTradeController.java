package com.goodsmarket.jh.used_trade;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.used_trade.domain.Comment;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
import com.goodsmarket.jh.used_trade.dto.ItemDTO;
import com.goodsmarket.jh.used_trade.service.CommentService;
import com.goodsmarket.jh.used_trade.service.FileImageService;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;

@RequestMapping("/usedtrade")
@Controller
public class UsedTradeController {
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	private FileImageService fileImageService;
	private CommentService commentService;
	
	public UsedTradeController(UsedTradeService usedTradeService, FavoriteService favoriteService, FileImageService fileImageService, CommentService commentService)
	{
		this.usedTradeService = usedTradeService;
		this.favoriteService =  favoriteService;
		this.fileImageService = fileImageService;
		this.commentService = commentService;
	}
	
	@GetMapping("/list-view")
	public String goodsList(Model model)
	{
		List<BoardDTO> usedTradeList = usedTradeService.getAllUsedTrade();

		model.addAttribute("usedTradeList", usedTradeList);
		
		return "usedtrade/list";
	}
	
	@GetMapping("/create-view")
	public String goodsCreate()
	{
		return "usedtrade/input";
	}
	
	@GetMapping("/detail-view")
	public String goodsShow(Model model
			, @RequestParam("id") int id)
	{		
		ItemDTO usedTrade = usedTradeService.getUsedTrade(id);
		
		model.addAttribute("usedTrade", usedTrade);
		
		// 선택한 게시글의 즐겨찾기 수 조회
		int shoppingCartCount =  favoriteService.getAllFavoriteCount(id);
		
		model.addAttribute("shoppingCartCount", shoppingCartCount);
		
		List<Comment> commentsList = commentService.getAllCommentsByUsedTradeId(id);
		
		model.addAttribute("commentsList", commentsList);
		
		return "usedtrade/detail";
	}
	
	@GetMapping("/update-view")
	public String goodsUpdate(@RequestParam("id") int id
			, Model model)
	{
		ItemDTO usedTrade = usedTradeService.getUsedTrade(id);
		model.addAttribute("usedTrade", usedTrade);
		
		return "/usedTrade/update";
	}
	
	@GetMapping("/search-view")
	public String search(@RequestParam("title") String title
			, Model model)
	{
		List<BoardDTO> boardList = usedTradeService.getAllUsedTradeByTitle(title);
		
		model.addAttribute("usedTradeList", boardList);
		
		return "/usedTrade/search";
	}
}
