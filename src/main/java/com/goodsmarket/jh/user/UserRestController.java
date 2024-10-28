package com.goodsmarket.jh.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.favorite.service.FavoriteService;
import com.goodsmarket.jh.used_trade.service.UsedTradeService;
import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private UserService userService;
	private UsedTradeService usedTradeService;
	private FavoriteService favoriteService;
	
	@Autowired
	public UserRestController(UserService userService, UsedTradeService usedTradeService, FavoriteService favoriteService)
	{
		this.userService = userService;
		this.usedTradeService = usedTradeService;
		this.favoriteService = favoriteService;
	}
	
	@PostMapping("join")
	public Map<String, String> join(@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("phone") String phone
			, @RequestParam("email") String email
			, @RequestParam("nickName") String nickName)
	{
		int count = userService.addUser(loginId, password, name, phone, email, nickName);
		
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
	
	@PostMapping("login")
	public Map<String, String> login(@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpSession session)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		User user = userService.getUser(loginId, password);
				
		if(user != null)
		{
			// 로그인 한 사용자의 세션 값 저장
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 아이디 중복확인 기능
	@PostMapping("duplicatedId")
	public Map<String, String> duplicatedId(@RequestParam("loginId") String loginId)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		int count = userService.checkUserId(loginId);
		
		if(count == 0)
		{
			resultMap.put("result", "success");
		}
		else
		{
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 회원탈퇴 API
		@DeleteMapping("/delete")
		public Map<String, String> deleteUser(@RequestParam("id") int id)
		{
			Map<String, String> resultMap = new HashMap<>();
			
			int count = userService.removeUser(id);
			
			// 즐겨찾기 개수 리턴 후 1개 이상인 경우 삭제 되게 구현해야함
			//int favoriteCount = favoriteService.removeAllShoppingCart(id);
			//int usedTradeCount = usedTradeService.removeAllUsedTrade(id);
			
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
