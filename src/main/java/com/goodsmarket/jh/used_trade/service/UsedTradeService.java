package com.goodsmarket.jh.used_trade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.used_trade.domain.FileImage;
import com.goodsmarket.jh.used_trade.domain.UsedTrade;
import com.goodsmarket.jh.used_trade.dto.BoardDTO;
import com.goodsmarket.jh.used_trade.dto.ItemDTO;
import com.goodsmarket.jh.used_trade.repository.FileImageRepository;
import com.goodsmarket.jh.used_trade.repository.UsedTradeRepository;

@Service
public class UsedTradeService {
	private UsedTradeRepository usedTradeRepository;
	private FileImageRepository fileImageRepository;
	private FileImageService fileImageService;
	
	@Autowired
	public UsedTradeService(UsedTradeRepository usedTradeRepository, FileImageRepository fileImageRepository, FileImageService fileImageService)
	{
		this.usedTradeRepository = usedTradeRepository;
		this.fileImageRepository = fileImageRepository;
		this.fileImageService = fileImageService;
	}
	
	// 판매 작성글 저장 Service		---> 게시물 id(autoincrement) 값 가져오기 위해 useGeneratedKeys 사용해야함으로 파라미터 타입을 객체로 바꿔줘야함
	public int addUsedTrade(UsedTrade usedTrade, int userId, MultipartFile[] files)
	{
		int count = usedTradeRepository.insertUsedTrade(usedTrade);		// 게시글의 PK를 반환받아야하니 파일 생성보다 게시글 생성을 먼저 해줘야함
		
		// 파일 저장
		if(files != null)
		{
			for(int i = 0; i < files.length; i++)
			{
				String imagePath = FileManager.saveFile(userId, files[i]);			 
				fileImageRepository.insertFileImage(usedTrade.getId(), userId, imagePath);
			}			
		}
			
		return count;
	}
	
	// 전체 판매목록 불러오기 Service		--> DTO 작성 필요 : 게시물의 정보와 파일 정보를 합쳐야함
	public List<BoardDTO> getAllUsedTrade()
	{
		List<UsedTrade> usedTradeList = usedTradeRepository.selectAllUsedTrade();
	
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		// 각 게시글의 첫번째 파일 가져오기
		for(int i = 0; i < usedTradeList.size(); i++)
		{
			BoardDTO boardDTO = new BoardDTO();
			
			int id = usedTradeList.get(i).getId();
			String title = usedTradeList.get(i).getTitle();
			String contents = usedTradeList.get(i).getContents();
			String place = usedTradeList.get(i).getPlace();
			String addTradingPlace = usedTradeList.get(i).getAddTradingPlace();
			String sellerName = usedTradeList.get(i).getSellerName();
			
			boardDTO.setId(id);
			boardDTO.setTitle(title);
			boardDTO.setContents(contents);
			boardDTO.setPlace(place);
			boardDTO.setAddTradingPlace(addTradingPlace);
			boardDTO.setSellerName(sellerName);
			
			
			// 이미지가 담긴 게시글이라면
			if(!fileImageService.getFileImages(id).isEmpty())
			{
				String fileImage = fileImageService.getFileImages(id).get(0).getImagePath();
				boardDTO.setImagePath(fileImage);
			}
			else
			{
				boardDTO.setImagePath(null);
			}
			
			boardDTOList.add(boardDTO);
		}
		
		return boardDTOList;
	}
	
	// 물품 검색 기능에 사용되는 Service
	public List<UsedTrade> getAllUsedTradeByTitle(String title)
	{
		return usedTradeRepository.selectAllUsedTradeByTitle(title);
	}
	
	// 상세페이지 불러오기 Service
	public ItemDTO getUsedTrade(int id)
	{
		UsedTrade usedTrade = usedTradeRepository.selectUsedTrade(id);
		
		List<FileImage> fileImage = fileImageService.getFileImages(id);
		List<String> imageList = new ArrayList<>();
		
		// 해당하는 게시글에 저장된 이미지 불러오기
		if(!fileImage.isEmpty())
		{
			for(int i = 0; i < fileImage.size(); i++)
			{
				String imagePath;
				imagePath = fileImage.get(i).getImagePath();
				imageList.add(imagePath);
			}
		}
		else
		{
			imageList.add(null);
		}
		
		ItemDTO item = new ItemDTO();
		
		item.setId(usedTrade.getId());
		item.setUserId(usedTrade.getUserId());
		item.setTitle(usedTrade.getTitle());
		item.setContents(usedTrade.getContents());
		item.setPlace(usedTrade.getPlace());
		item.setAddTradingPlace(usedTrade.getAddTradingPlace());
		item.setSellerName(usedTrade.getSellerName());
		item.setSellPrice(usedTrade.getSellPrice());
		item.setFileImage(imageList);
		item.setViews(usedTrade.getViews());
		item.setCreatedAt(usedTrade.getCreatedAt());
		item.setUpdatedAt(usedTrade.getUpdatedAt());
		
		return item;
	}
	
	// 게시글 조회수 Service
	public boolean countUpUsedTradeViews(int id)
	{
		int count = usedTradeRepository.updateUsedTradeViews(id);
		
		if(count >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// 사용자가 작성한 게시글 정보 조회 Service
	public List<UsedTrade> getUsedTradeByUserId(int userId)
	{
		return usedTradeRepository.selectUsedTradeByUserId(userId);
	}
	
	// 사용자가 작성한 모든 게시글 삭제 Service
	public int removeAllUsedTrade(int userId)
	{
		// 사용자가 업로드한 모든 파일 이미지 조회
		List<FileImage> fileList =fileImageService.getAllFileImagesByUserId(userId);
		
		// 폴더에서 사용자가 업로드한 파일 제거		
		for(int i = 0; i < fileList.size(); i++)
		{
			String imagePath = fileList.get(i).getImagePath();
			FileManager.removeFile(imagePath);
		}			

		
		// 사용자가 작성한 모든 파일 삭제
		fileImageService.removeFileImagesByUserId(userId);
						
		return usedTradeRepository.deleteAllUsedTrade(userId);
	}
	
	// 사용자가 작성한 게시글 삭제 Service
	public int removeUsedTradeById(int id)
	{
		// 해당 게시글의 이미지 추출
		List<FileImage> fileImageList = fileImageService.getFileImages(id);
		
		for(int i = 0; i < fileImageList.size(); i++)
		{
			String imagePath = fileImageList.get(i).getImagePath();
			
			FileManager.removeFile(imagePath);
		}
		
		fileImageService.removeFileImagesByUsedTradeId(id);
		
		return usedTradeRepository.deleteUsedTradeById(id);
	}
	
	// 게시글 수정 Service
	public int changeUsedTrade(int id, int userId, String title, String contents, List<MultipartFile> file, String location, String addTradingPlace, int sellPrice)
	{
		fileImageService.changeFileImagesByUsedTradeId(id, file);
		
		return usedTradeRepository.updateUsedTrade(userId, title, contents, addTradingPlace, addTradingPlace, sellPrice);
	}
	
	// 게시글 리스트의 각 id값 반환 Service
	public int[] getAllUsedTradeId()
	{
		List<UsedTrade> usedTradeList = usedTradeRepository.selectAllUsedTrade();
		
		int id[] = new int[usedTradeList.size()];
		
		// 반복문으로 각 행의 정보를 추출
		for(int i = 0; i < usedTradeList.size(); i++)
		{
			id[i] = usedTradeList.get(i).getId();
		}
		
		return id;
	}
}
