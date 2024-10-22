package com.goodsmarket.jh.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	@GetMapping("/join-view")
	public String join()
	{
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String login()
	{
		return "user/login";
	}
	
	@GetMapping("/additional/join-view")
	public String additionalLogin()
	{
		return "user/additionalJoin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("profileImage");
		
		return "redirect:/user/login-view";
	}
}
