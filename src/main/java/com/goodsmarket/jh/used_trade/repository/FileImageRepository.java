package com.goodsmarket.jh.used_trade.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodsmarket.jh.used_trade.domain.FileImage;

@Mapper
public interface FileImageRepository {
	// 파일 업로드 Repository
	public int insertFileImage(@Param("usedTradeId") int usedTradeId
			, @Param("userId") int userId
			, @Param("imagePath") String imagePath);
	
	// 전체 파일 출력 Repository : 판매 전체글에서 각 게시글에 해당하는 이미지들을 추출
	public List<FileImage> selectAllFileImage(@Param("usedTradeId") int usedTradeId);
}
