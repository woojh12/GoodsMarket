package com.goodsmarket.jh.favorite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.favorite.repository.FavoriteRepository;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;
import com.goodsmarket.jh.used_trade.repository.UsedTradeRepository;

@Service
public class FavoriteService {
	private FavoriteRepository favoriteRepository;
	private UsedTradeRepository usedTradeRepository;
	private FileImageRepository fileImageRepository;
	
	@Autowired
	public FavoriteService(FavoriteRepository favoriteRepository, UsedTradeRepository usedTradeRepository, FileImageRepository fileImageRepository)
	{
		this.favoriteRepository = favoriteRepository;
		this.usedTradeRepository = usedTradeRepository;
		this.fileImageRepository = fileImageRepository;
	}
	
	// 즐겨찾기 담기 Service
	public int addShoppingCart(int usedTradeId, int userId)
	{
		return favoriteRepository.insertShoppingCart(usedTradeId, userId);
	}
	
	// 즐겨찾기 체크 Service
	public int getShoppingCartCount(int userTradeId, int userId)
	{
		return favoriteRepository.countShoppingCart(userTradeId, userId);
	}
	
	// 즐겨찾기 한개만 삭제 Service
	public int removeShoppingCartCount(int userTradeId, int userId)
	{
		return favoriteRepository.deleteShoppingCart(userTradeId, userId);
	}
	
	// 즐겨찾기 모두 삭제 
	public int removeAllShoppingCart(int userId)
	{
		return favoriteRepository.deleteAllShoppingCart(userId);
	}
	
	// 즐겨찾기 선택한 모든 인원수 조회 service
	public int getAllShoppingCartCount(int userTradeId)
	{
		return favoriteRepository.countAllShoppingCart(userTradeId);
	}
	
	// 즐겨찾기에 담긴 게시글 정보 가져오기 service
	public List<BoardDTO> getUserShopingCartList(int userId)
	{
		// 사용자가 누른 즐겨찾기 버튼 게시물의 id 값 조회
		int[] usedTradeId = favoriteRepository.selectUserShopingCartList(userId);
		
		// 게시물 정보를 담을 객체 리스트 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		// 게시물 정보를 조회 후 게시물 리스트에 저장
		for(int i = 0; i < usedTradeId.length; i++)
		{
			BoardDTO board = new BoardDTO();
			
			// 게시물 정보를 임시로 저장하는 객체 생성
			UsedTrade usedTrade = usedTradeRepository.selectUsedTrade(usedTradeId[i]);
			
			int id = usedTrade.getId();
			String title = usedTrade.getTitle();
			String contents = usedTrade.getContents();
			String place = usedTrade.getPlace();
			String addTradingPlace = usedTrade.getAddTradingPlace();
			String sellerName = usedTrade.getSellerName();
			
			// 각 게시물의 첫번째 이미지 추출
			String imagePath = fileImageRepository.selectAllFileImage(usedTradeId[i]).get(0).getImagePath();
			
			board.setId(id);
			board.setTitle(title);
			board.setContents(contents);
			board.setPlace(place);
			board.setAddTradingPlace(addTradingPlace);
			board.setSellerName(sellerName);
			board.setImagePath(imagePath);
			
			boardList.add(board);
		}
		
		return boardList;
	}
}
