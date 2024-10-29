package com.goodsmarket.jh.buy.domain;

import java.time.LocalDateTime;

public class Buy {
	private int id;
	private int usedTradeId;
	private int userId;
	private LocalDateTime createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsedTradeId() {
		return usedTradeId;
	}
	public void setUsedTradeId(int usedTradeId) {
		this.usedTradeId = usedTradeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
