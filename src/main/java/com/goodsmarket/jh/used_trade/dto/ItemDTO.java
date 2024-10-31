package com.goodsmarket.jh.used_trade.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.goodsmarket.jh.used_trade.domain.Comment;

public class ItemDTO {
	private int id;
	private int userId;
	private int	buyerId;				// 구매한 사용자의 id 
	private String title;
	private String contents;
	private String place;
	private String addTradingPlace;		// 사용자가 주소를 추가적으로 적은 것을 저장하는 변수
	private String sellerName;
	private int sellPrice;
	private List<String> fileImage;
	private int views;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddTradingPlace() {
		return addTradingPlace;
	}
	public void setAddTradingPlace(String addTradingPlace) {
		this.addTradingPlace = addTradingPlace;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public List<String> getFileImage() {
		return fileImage;
	}
	public void setFileImage(List<String> fileImage) {
		this.fileImage = fileImage;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
