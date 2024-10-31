package com.goodsmarket.jh.used_trade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.service.CommentService;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/usedtrade")
public class UsedTradeRestController {
	private UsedTradeService usedTradeService;
	
	@Autowired
	public UsedTradeRestController(UsedTradeService usedTradeService)
	{
		this.usedTradeService = usedTradeService;
	}
	
	// 판매 작성글 API
	@PostMapping("/create")
	public Map<String, String> usedTradeCreate(@ModelAttribute UsedTrade usedTrade
			, @RequestParam(value="inputFile", required=false) MultipartFile[] files
			, HttpSession session)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		int userId = (Integer)session.getAttribute("userId");
		int count = usedTradeService.addUsedTrade(usedTrade, userId, files);
		
		if(count != 0)
		{
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 조회수 API
	@PostMapping("/increase/views")
	public Map<String, String> increaseViews(@RequestParam("id") int id)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		boolean check = usedTradeService.countUpUsedTradeViews(id);
		
		if(check == true)
		{
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 게시글 삭제
	@DeleteMapping("/delete")
	public Map<String, String> usedTradeDelete(@RequestParam("id") int id)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		int count = usedTradeService.removeUsedTradeById(id);
		
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
	
	// 게시글 수정
	@PutMapping("/update")
	public Map<String, String> usedTradeUpdate(@RequestParam("id") int id
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value="inputFile", required=false) List<MultipartFile> files
			, @RequestParam("place") String place
			, @RequestParam(value="addTradingPlace", required=false) String addTradingPlace
			, @RequestParam("sellPrice") int sellPrice
			, HttpSession session)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = usedTradeService.changeUsedTrade(id, userId, title, contents, files, place, addTradingPlace, sellPrice);
		
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
