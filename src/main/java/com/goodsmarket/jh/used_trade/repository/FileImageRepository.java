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
	
	// 각 게시글의 전체 파일 출력 Repository : 판매 전체글에서 각 게시글에 해당하는 이미지들을 추출
	public List<FileImage> selectAllFileImage(@Param("usedTradeId") int usedTradeId);
	
	// 사용자가 작성한 게시글의 업로드한 파일 삭제 Repository
	public int deleteFileImageByUsedTradeId(@Param("usedTradeId") int usedTradeId);
	
	// 사용자가 업로드한 전체 파일 삭제  Repository
	public int deleteAllFileImageByUserId(@Param("userId") int userId);
	
	// 사용자가 업로드한 전체 파일 출력 Repository
	public List<FileImage> selectAllFileImageByUserId(@Param("userId") int userId);
	
	// 사용자가 업로드한 파일 수정 Repository
	public int updateFileImageByUsedTradeId(@Param("usedTradeId") int usedTradeId, @Param("imagePath") String imagePath);
}
