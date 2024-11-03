package com.goodsmarket.jh.sell.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.sell.domain.Sell;
import com.goodsmarket.jh.sell.repository.SellRepository;
import com.goodsmarket.jh.used_trade.domain.FileImage;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;
import com.goodsmarket.jh.used_trade.repository.UsedTradeRepository;

@Service
public class SellService {
	private SellRepository sellRepository;
	private UsedTradeRepository usedTradeRepository;
	private FileImageRepository fileImageRepository;
	
	@Autowired
	public SellService(SellRepository sellRepository, UsedTradeRepository usedTradeRepository, FileImageRepository fileImageRepository)
	{
		this.sellRepository = sellRepository;
		this.usedTradeRepository = usedTradeRepository;
		this.fileImageRepository = fileImageRepository;
	}
	
	// 사용자가 판매한 내역을 저장하는 Service
	public int addSell(int usedTradeId, int userId)
	{
		return sellRepository.insertSell(usedTradeId, userId);
	}
	
	// 해당 게시글이 판매완료된 게시글인지 확인하는 Service
	public Sell getSell(int usedTradeId)
	{
		return sellRepository.selectSellByUsedTradeId(usedTradeId);
	}
	
	// 사용자가 판매한 물품 게시글 정보를 출력하는 Service
	public List<BoardDTO> getAllSellByUserId(int userId)
	{
		// 사용자가 판매한 게시물의 아이디 리스트 가져오기
		List<Sell> sellList = sellRepository.selectAllSellByUserId(userId);
		
		// 전체 화면에서 보여지는 데이터 담기
		List<BoardDTO> boardList = new ArrayList<>();
		
		// 각 게시물의 정보를 저장하는 부분
		for(int i = 0; i < sellList.size(); i++)
		{
			// 판매한 게시물의 id 가져오기
			UsedTrade usedTrade = usedTradeRepository.selectUsedTrade(sellList.get(i).getUsedTradeId());
			
			BoardDTO board = new BoardDTO(); 
			
			board.setId(usedTrade.getId());
			board.setTitle(usedTrade.getTitle());
			board.setPlace(usedTrade.getPlace());
			board.setAddTradingPlace(usedTrade.getAddTradingPlace());
			board.setSellerName(usedTrade.getSellerName());
			
			// 게시글 id로 파일 이미지 리스트를 추출하는 부분
			List<FileImage> fileImageList = fileImageRepository.selectAllFileImage(usedTrade.getId());
			
			// 각 게시물의 첫번째 이미지를 저장하는 부분
			if(!fileImageList.isEmpty())
			{
				String imagePath = fileImageList.get(0).getImagePath();
				board.setImagePath(imagePath);
			}
			else
			{
				board.setImagePath(null);				
			}
					
			boardList.add(board);
		}
			
		return boardList;
	}
	
	// 구매한 물품의 개수를 출력하는 Service
	public int getCountAllByUserId(int userId)
	{
		return sellRepository.countAllByUserId(userId);
	}
}
