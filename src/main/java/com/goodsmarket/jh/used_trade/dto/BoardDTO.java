package com.goodsmarket.jh.used_trade.dto;

public class BoardDTO {
	private int id;
	private int buyerId;				// 물품 구매한 사용자 id
	private String title;
	private String contents;
	private String place;
	private String addTradingPlace;		// 사용자가 주소를 추가적으로 적은 것을 저장하는 변수
	private String sellerName;
	private String imagePath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
