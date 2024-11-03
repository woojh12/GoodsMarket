package com.goodsmarket.jh.buy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.buy.domain.Buy;
import com.goodsmarket.jh.buy.repository.BuyRepository;
import com.goodsmarket.jh.used_trade.domain.FileImage;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;
import com.goodsmarket.jh.used_trade.repository.UsedTradeRepository;

@Service
public class BuyService {
	private BuyRepository buyRepository;
	private UsedTradeRepository usedTradeRepository;
	private FileImageRepository fileImageRepository;
	
	@Autowired
	public BuyService(BuyRepository buyRepository, UsedTradeRepository usedTradeRepository, FileImageRepository fileImageRepository)
	{
		this.buyRepository = buyRepository;
		this.usedTradeRepository = usedTradeRepository;
		this.fileImageRepository = fileImageRepository;
	}
	
	// 구매한 물품을 추가하는 Service
	public int addUsedTrade(int usedTradeId, int userId)
	{
		return buyRepository.insertUsedTrade(usedTradeId, userId);
	}
	
	// 사용자가 구매한 전체 물품을 출력하는 Service			---> 마이페이지 view에서 사용
	public List<BoardDTO> getAllBuyByUserId(int userId)
	{
		// 사용자가 구매한 정보를 담는 리스트
		List<Buy> buyList = buyRepository.selectAllBuyByUserId(userId);
		
		// 전체 화면에서 보여지는 데이터 담기
		List<BoardDTO> boardList = new ArrayList<>();
		
		for(int i = 0; i < buyList.size(); i++)
		{
			BoardDTO board = new BoardDTO();
			
			// 게시글 id로 게시물의 정보를 불러오기
			UsedTrade usedTrade = usedTradeRepository.selectUsedTrade(buyList.get(i).getUsedTradeId());
			
			// 게시글 id로 게시물에 저장된 이미지 불러오기
			List<FileImage> fileImageList = fileImageRepository.selectAllFileImage(buyList.get(i).getUsedTradeId()); 
			
			int id = usedTrade.getId();
			String title = usedTrade.getTitle();
			String place = usedTrade.getPlace();
			String addTradingPlace = usedTrade.getAddTradingPlace();
			String sellerName = usedTrade.getSellerName();
			
			board.setId(id);
			board.setTitle(title);
			board.setPlace(place);
			board.setAddTradingPlace(addTradingPlace);
			board.setSellerName(sellerName);
			
			// 이미지가 있는 경우면
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
	
	// 물품 구매한 사용자 정보 확인 Service	---> 상세 페이지에서 사용
	public Buy getBuyByUsedTradeId(int usedTradeId)
	{
		return buyRepository.selectBuyByUsedTradeId(usedTradeId);
	}
	
	// 구매 취소하는 Service
	public int removeBuy(int usedTradeId, int userId)
	{
		return buyRepository.deleteBuy(usedTradeId, userId);
	}
	
	// 구매한 물품의 개수를 출력하는 Service
	public int getCountAllByUserId(int userId)
	{
		return buyRepository.countAllByUserId(userId);
	}
}
