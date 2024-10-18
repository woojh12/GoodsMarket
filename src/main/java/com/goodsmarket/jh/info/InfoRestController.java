package com.goodsmarket.jh.info;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.info.service.InfoService;
import com.goodsmarket.jh.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/info")
@RestController
public class InfoRestController {
	private InfoService infoService;
	private UserService userService;
	
	@Autowired
	public InfoRestController(InfoService infoService, UserService userService)
	{
		this.infoService = infoService;
		this.userService = userService;
	}
	
	// 회원정보 수정 API
	@PostMapping("/update")
	public Map<String, String> updateInfo(HttpSession session
			, @RequestParam("password") String password
			, @RequestParam("phone") String phone
			, @RequestParam("email") String email
			, @RequestParam("nickName") String nickName
			, @RequestParam(value="imagePath", required=false) MultipartFile imagePath)
	{
		Map<String, String> resultMap = new HashMap<>();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = infoService.setUserInfo(userId, password, phone, email, nickName, imagePath);
		
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
