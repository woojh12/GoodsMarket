package com.goodsmarket.jh.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goodsmarket.jh.user.domain.User;
import com.goodsmarket.jh.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my")
public class InfoController {
	private UserService userService;
	
	@Autowired
	public InfoController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/info-view")
	public String info(Model model
			, HttpSession session)
	{
		int userId = (Integer)session.getAttribute("userId");
		
		User user = userService.getProfileImage(userId);
		
		System.out.println("이미지 : " + user.getImagePath());
		
		model.addAttribute("user", user);
		
		return "/info/myinfo";
	}
	
	@GetMapping("/info-update-view")
	public String infoUpdate()
	{
		return "/info/myinfoUpdate";
	}
	
	@GetMapping("/info-postList-view")
	public String infoPostList()
	{
		return "/info/postList";
	}
}
