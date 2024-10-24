package com.goodsmarket.jh.used_trade.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileImageRepository {
	public int insertFileImage(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId
			, @Param("imagePath") String imagePath);
}
