package com.goodsmarket.jh.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodsmarket.jh.shoppingcart.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	public ShoppingCartService(ShoppingCartRepository shoppingCartRepository)
	{
		this.shoppingCartRepository = shoppingCartRepository;
	}
	
	// 장바구니 담기 Service
	public int addShoppingCart(int usedTradeId, int userId)
	{
		return shoppingCartRepository.insertShoppingCart(usedTradeId, userId);
	}
	
	// 장바구니 체크 Service
	public int getShoppingCartCount(int userTradeId, int userId)
	{
		return shoppingCartRepository.countShoppingCart(userTradeId, userId);
	}
	
	// 장바구니 삭제 Service
	public int removeShoppingCartCount(int userTradeId, int userId)
	{
		return shoppingCartRepository.deleteShoppingCart(userTradeId, userId);
	}
	
	// 장바구니 선택한 모든 인원수 조회 Service
	public int getAllShoppingCartCount(int userTradeId)
	{
		return shoppingCartRepository.countAllShoppingCart(userTradeId);
	}
}
