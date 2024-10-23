package com.goodsmarket.jh.filetest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.filetest.service.FileTestService;

@RestController
public class TestRestController {
	@Autowired
	private FileTestService fileTestService;

	@PostMapping("/add")
	public String addfile(@RequestParam("file") List<MultipartFile> list)
	{
		//리스트에서 빈값은 제거
		list=list.stream().filter((x)->x.isEmpty()==false ).collect(Collectors.toList());
		
		int count = 0;
		for(MultipartFile file:list)
		{
			count = fileTestService.addFile(file);
		}
		
		if(count != 0)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}
}
