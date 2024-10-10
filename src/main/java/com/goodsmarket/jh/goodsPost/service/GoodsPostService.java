package com.goodsmarket.jh.goodsPost.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.goodsPost.domain.GoodsPost;
import com.goodsmarket.jh.goodsPost.repository.GoodsPostRepository;

@Service
public class GoodsPostService {
	private GoodsPostRepository goodsPostRepository;
	
	@Autowired
	public GoodsPostService(GoodsPostRepository goodsPostRepository)
	{
		this.goodsPostRepository = goodsPostRepository;
	}
	
	// 판매 작성글 저장 Service
	public int addUsedTrade(int userid
			, String title
			, String contents
			, MultipartFile file
			, String location
			, String sellerName
			, int sellPrice)
	{
		String imagePath = FileManager.saveFile(userid, file);
		
		return goodsPostRepository.insertUsedTrade(userid, title, contents, imagePath, location, sellerName, sellPrice);
	}
	
	// 전체 판매목록 불러오기 Service
	public List<GoodsPost> getAllUsedTrade()
	{
		return goodsPostRepository.selectAllUsedTrade();
	}
	
	// 선택된 판매글 불러오기 Service
	public GoodsPost getUsedTrade(int id)
	{
		return goodsPostRepository.selectUsedTrade(id);
	}
	
	// 게시글 조회수 Service
	public boolean countUpUsedTradeViews(int id)
	{
		int count = goodsPostRepository.updateUsedTradeViews(id);
		
		if(count >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
