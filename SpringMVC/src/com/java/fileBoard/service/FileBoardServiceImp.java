package com.java.fileBoard.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.fileBoard.dao.FileBoardDao;
import com.java.fileBoard.dto.FileBoardDto;

public class FileBoardServiceImp implements FileBoardService {
	private FileBoardDao fileBoardDao;

	public void setFileBoardDao(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}

	public FileBoardServiceImp() {	}

	public FileBoardServiceImp(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}

	@Override
	public void fileBoardWrite(ModelAndView mav) {
		// 최초 부모 글일때
		int boardNumber = 0;		// 부모글이면 0
		int groupNumber = 1;		// 그룹번호
		int sequenceNumber = 0;		// 글 순서
		int sequenceLevel = 0;		// 글 들여쓰기
		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// 답변일때 boardNumber는 부모글에 따라 글 번호, 그룹 번호, 글 순서, 들여쓰기에 맞춰 변경된다.
		if(request.getParameter("boardNumber") != null) {
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));			
		}
		
		HAspect.logger.info(HAspect.logMsg + boardNumber + "\t" + groupNumber + "\t" + sequenceNumber + "\t" + sequenceLevel);
		
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("groupNumber",groupNumber);
		mav.addObject("sequenceNumber",sequenceNumber);
		mav.addObject("sequenceLevel",sequenceLevel);
		
		mav.setViewName("fileBoard/write");
		
	}
	
	
	@Override
	public void fileBoardWriteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		FileBoardDto fileBoardDto = (FileBoardDto) map.get("fileBoardDto");
		
		// MultipartHttpServletRequest 기존에 formfiled로 들어왔는지 확인하지 않아도 됀다. (파일게시판에 굉장히 좋은방법이라는데...)
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);
		
		writeNumber(fileBoardDto);
		
		HAspect.logger.info(HAspect.logMsg + fileBoardDto);
		
		int check = fileBoardDao.fileBoardWriteOk(fileBoardDto);
		
		HAspect.logger.info(HAspect.logMsg + "here");
		HAspect.logger.info(HAspect.logMsg + fileBoardDto);
		
		
		mav.addObject("check",check);
		mav.setViewName("fileBoard/writeOk");
	}
	
	
	public void uploadFile(MultipartHttpServletRequest request, FileBoardDto fileBoardDto) {
	}
	
	public void writeNumber(FileBoardDto fileBoardDto) {
		int boardNumber = fileBoardDto.getBoardNumber();
		int groupNumber = fileBoardDto.getGroupNumber();
		int sequenceNumber = fileBoardDto.getSequenceNumber();
		int sequenceLevel = fileBoardDto.getSequenceLevel();
		
		
		if(boardNumber == 0) {		// 부모 글일때!
			int max = fileBoardDao.fileBoardGroupNumberMax();
			
			if(max != 0) {
				fileBoardDto.setGroupNumber(max + 1);
			}
			HAspect.logger.info(HAspect.logMsg+max);
		} else {
			HashMap<String, Integer> hmap = new HashMap<String, Integer>();
			hmap.put("groupNumber",groupNumber);
			hmap.put("sequenceNumber", sequenceNumber);
			
			int check = fileBoardDao.fileBoardWriteNumber(hmap);
			HAspect.logger.info(HAspect.logMsg+check);
			
			sequenceNumber += 1;
			sequenceLevel += 1;
			fileBoardDto.setSequenceNumber(sequenceNumber);
			fileBoardDto.setSequenceLevel(sequenceLevel);
		}
	}
	
	@Override
	public void fileBoardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// paging
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) {
			pageNumber = "1";
		}
		int currentPage = Integer.parseInt(pageNumber);
		
		int boardSize = 10;
		
		int startRow = (currentPage-1)*boardSize+1;
		int endRow = currentPage*boardSize;
		
		int count = fileBoardDao.fileBoardCount();
		HAspect.logger.info(HAspect.logMsg+count);
		
		List<FileBoardDto> boardList = null;
		
		if(count > 0) {
			boardList = fileBoardDao.fileBoardList(startRow,endRow);
			HAspect.logger.info(HAspect.logMsg+boardList);
		}
		
		mav.addObject("boardList",boardList);
		mav.addObject("boardSize",boardSize);
		mav.addObject("currentPage",currentPage);
		mav.addObject("count",count);
		mav.setViewName("fileBoard/list");
	}
	
	@Override
	public void fileBoardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		HAspect.logger.info(HAspect.logMsg + boardNumber + "\t" + pageNumber);
		
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardRead(boardNumber);
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardDto", fileBoardDto);
		
		mav.setViewName("fileBoard/read");
	}
	
	@Override
	public void fileBoardDownLoad(ModelAndView mav) {
	}
	@Override
	public void fileBoardDelete(ModelAndView mav) {
	}
	@Override
	public void fileBoardDeleteOk(ModelAndView mav) {
	}
	
	@Override
	public void fileBoardUpdate(ModelAndView mav) {
	}
	@Override
	public void fileBoardUpdateOk(ModelAndView mav) {
	}
}
