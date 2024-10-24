package com.goodsmarket.jh.used_trade.domain;

import java.time.LocalDateTime;

public class FileImage {
	private int id;
	private int usedTradeId;
	private int userId;
	private String imagePath;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
