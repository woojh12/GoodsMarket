package com.goodsmarket.jh.used_trade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodsmarket.jh.buy.domain.Buy;
import com.goodsmarket.jh.buy.service.BuyService;
import com.goodsmarket.jh.common.FileManager;
import com.goodsmarket.jh.sell.domain.Sell;
import com.goodsmarket.jh.sell.service.SellService;
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
	private BuyService buyService;
	private SellService sellService;
	
	@Autowired
	public UsedTradeService(UsedTradeRepository usedTradeRepository, FileImageRepository fileImageRepository, FileImageService fileImageService, BuyService buyService, SellService sellService)
	{
		this.usedTradeRepository = usedTradeRepository;
		this.fileImageRepository = fileImageRepository;
		this.fileImageService = fileImageService;
		this.buyService = buyService;
		this.sellService = sellService;
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
	public List<BoardDTO> getAllUsedTradeByTitle(String title)
	{
		List<UsedTrade> usedTradeList = usedTradeRepository.selectAllUsedTradeByTitle(title);
		List<BoardDTO> boardList = new ArrayList<>();
		
		// 각 게시글의 첫번째 파일 가져오기
		for(int i = 0; i < usedTradeList.size(); i++)
		{
			BoardDTO board= new BoardDTO();
			
			board.setId(usedTradeList.get(i).getId());
			board.setTitle(usedTradeList.get(i).getTitle());
			board.setContents(usedTradeList.get(i).getContents());
			board.setPlace(usedTradeList.get(i).getPlace());
			board.setAddTradingPlace(usedTradeList.get(i).getAddTradingPlace());
			board.setSellerName(usedTradeList.get(i).getSellerName());
			
			
			// 이미지가 담긴 게시글이라면
			if(!fileImageService.getFileImages(board.getId()).isEmpty())
			{
				String fileImage = fileImageService.getFileImages(board.getId()).get(0).getImagePath();
				board.setImagePath(fileImage);
			}
			else
			{
				board.setImagePath(null);
			}
			
			boardList.add(board);
		}
		
		return boardList;
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
	
		// 구매한 사용자 정보를 저장하는 부분
		Buy buy = buyService.getBuyByUsedTradeId(id);
		
		if(buy != null)
		{
			item.setBuyerId(buy.getUserId());			
		}
		
		// 판매된 물품인지 확인하는 부분
		Sell sell = sellService.getSell(id);
		
		if(sell != null)
		{
			item.setSell(true);
		}
		else
		{
			item.setSell(false);
		}
		
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
	public List<BoardDTO> getUsedTradeByUserId(int userId)
	{
		// 사용자가 작성한 게시글 리스트 불러오기
		List<UsedTrade> usedTradeList = usedTradeRepository.selectUsedTradeByUserId(userId);
		
		// 게시글 정보를 담는 board 리스트 생성
		List<BoardDTO> boardList = new ArrayList<>();
		
		// 게시글 정보들을 boardList에 담는 과정
		for(int i = 0; i < usedTradeList.size(); i++)
		{
			// 게시글 id로 게시물에 저장된 이미지 불러오기
			List<FileImage> fileImageList = fileImageRepository.selectAllFileImage(usedTradeList.get(i).getId()); 
						
			BoardDTO board = new BoardDTO();
			
			int id = usedTradeList.get(i).getId();
			String title = usedTradeList.get(i).getTitle();
			String place = usedTradeList.get(i).getPlace();
			String addTradingPlace = usedTradeList.get(i).getAddTradingPlace();
			String sellerName = usedTradeList.get(i).getSellerName();
			
			board.setId(id);
			board.setTitle(title);
			board.setPlace(place);
			board.setAddTradingPlace(addTradingPlace);
			board.setSellerName(sellerName);
			
			// 이미지가 있는 경우면
			if(!fileImageList.isEmpty())
			{
				String imagePath = fileImageList.get(0).getImagePath();
							board.setImagePath(imagePath);
			}
			else
			{
				board.setImagePath(null);
			}
						
			boardList.add(board);
		}
		
		return boardList;
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
	public int changeUsedTrade(int id, int userId, String title, String contents, List<MultipartFile> files, String place, String addTradingPlace, int sellPrice)
	{	
		// 변경된 파일이 있는 경우
		if(files != null)
		{
			// 변경되기 전의 이미지 파일 삭제 
			List<FileImage> deleteImagePathList = fileImageRepository.selectAllFileImage(id);
			
			if(deleteImagePathList != null)
			{
				for(int i = 0; i < deleteImagePathList.size(); i++)
				{
					String deleteImagePath = deleteImagePathList.get(i).getImagePath();
					FileManager.removeFile(deleteImagePath);
				}			
				fileImageRepository.deleteFileImageByUsedTradeId(id);
			}
			
			// 변경된 파일 수정
			if(files != null)
			{
				for(int i = 0; i < files.size(); i++)
				{
					String imagePath = FileManager.saveFile(userId, files.get(i));
					fileImageRepository.insertFileImage(id, userId, imagePath);
				}
			}
		}
		else	// 변경된 파일이 없는 경우
		{
			// 변경되기 전의 이미지 파일 삭제 
			List<FileImage> deleteImagePathList = fileImageRepository.selectAllFileImage(id);
						
			if(deleteImagePathList != null)
			{
				for(int i = 0; i < deleteImagePathList.size(); i++)
				{
					String deleteImagePath = deleteImagePathList.get(i).getImagePath();
					FileManager.removeFile(deleteImagePath);
				}			
				fileImageRepository.deleteFileImageByUsedTradeId(id);
			}
		}
		
		return usedTradeRepository.updateUsedTrade(userId, title, contents, place, addTradingPlace, sellPrice);
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
