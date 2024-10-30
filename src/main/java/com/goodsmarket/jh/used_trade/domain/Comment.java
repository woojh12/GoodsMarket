package com.goodsmarket.jh.used_trade.domain;

import java.time.LocalDateTime;

public class Comment {
	private int id;
	private int usedTradeId;
	private int userId;
	private String nickName;
	private String contents;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
