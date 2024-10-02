package com.goodsmarket.jh.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goodsmarket.jh.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService)
	{
		this.userService = userService;
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
}
