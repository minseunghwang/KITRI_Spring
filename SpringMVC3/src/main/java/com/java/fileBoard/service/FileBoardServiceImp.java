package com.java.fileBoard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.fileBoard.dao.FileBoardDao;
import com.java.fileBoard.dto.FileBoardDto;

@Component
public class FileBoardServiceImp implements FileBoardService {
	@Autowired
	private FileBoardDao fileBoardDao;

	
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
		
		// MultipartHttpServletRequest. 기존 formfeild로 들어왔는지 확인하지 않아도 된다. (파일게시판에 굉장히 좋은방법이라는데...)
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);
		
		writeNumber(fileBoardDto);
		
		HAspect.logger.info(HAspect.logMsg + fileBoardDto);
		
		MultipartFile upFile = request.getFile("file");
		
		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			long fileSize = upFile.getSize();
			
			File path = new File("C:\\pds\\");
			
			path.mkdir();
			
			if(path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);
					
					fileBoardDto.setPath(file.getAbsolutePath());
					fileBoardDto.setFileName(fileName);
					fileBoardDto.setFileSize(fileSize);
					HAspect.logger.info(HAspect.logMsg + fileName + "\t" + fileSize);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		int check = fileBoardDao.fileBoardWriteOk(fileBoardDto);
		
		HAspect.logger.info(HAspect.logMsg + "here");
		HAspect.logger.info(HAspect.logMsg + fileBoardDto);
		
		
		mav.addObject("check",check);
		mav.setViewName("fileBoard/writeOk");
	}
	
	
	public void uploadFile(MultipartHttpServletRequest request, FileBoardDto fileBoardDto) {

		MultipartFile upFile = request.getFile("file");

		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			long fileSize = upFile.getSize();
			
			File path = new File("C:\\pds\\");
			
			path.mkdir();
			
			if(path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);
					
					fileBoardDto.setPath(file.getAbsolutePath());
					fileBoardDto.setFileName(fileName);
					fileBoardDto.setFileSize(fileSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
		
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardRead(boardNumber);
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardDto", fileBoardDto);
		
		mav.setViewName("fileBoard/read");
	}
	
	@Override
	public void fileBoardDownLoad(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		HAspect.logger.info(HAspect.logMsg + boardNumber);
		
		FileBoardDto boardDto = fileBoardDao.fileBoardSelect(boardNumber);
		HAspect.logger.info(HAspect.logMsg + boardDto);
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			int index = boardDto.getFileName().indexOf('_')+1;
			String fileName = boardDto.getFileName().substring(index);
			String utfName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
			long fileSize = boardDto.getFileSize();
			String path = boardDto.getPath();
			
			// 고정문구에요! 외워야됌
			response.setHeader("Content-Disposition", "attachment;filename=\"" + utfName + "\"");
			// 8진수 설정
			response.setContentType("application/octet-stream");
			response.setContentLength((int)fileSize);
			
			bis = new BufferedInputStream(new FileInputStream(path),1024);
			bos = new BufferedOutputStream(response.getOutputStream(),1024);
			
			while(true) {
				int data = bis.read();
				if(data == -1) {
					break;
				}
				bos.write(data);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis != null) {
					bis.close();
				}
				if(bos != null) {
					bos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	@Override
	public void fileBoardDelete(ModelAndView mav) {
	}
	@Override
	public void fileBoardDeleteOk(ModelAndView mav) {
	}
	
	@Override
	public void fileBoardUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		FileBoardDto dto = fileBoardDao.fileBoardSelect(boardNumber);
		
		mav.addObject("boardDto",dto);
		mav.addObject("boardNumber",boardNumber);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName("fileBoard/update");
	}
	@Override
	public void fileBoardUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int fileDelCheck = Integer.parseInt(request.getParameter("fileDelCheck"));
		
		FileBoardDto updateDto = (FileBoardDto) map.get("fileBoardDto");
		
		uploadFile(request, updateDto);
		
		HAspect.logger.info(HAspect.logMsg + updateDto + "fileDelCheck : " + fileDelCheck);
		
		FileBoardDto dto = fileBoardDao.fileBoardSelect(boardNumber);
		if(fileDelCheck==1 && dto.getPath() != null) {
			File file = new File(dto.getPath());
			if(file.exists() && file.isFile()) {
				file.delete();
			}
		}
		int check = fileBoardDao.fileBoardUpdateOk(updateDto, fileDelCheck);
		mav.addObject("check",check);
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("boardNumber",boardNumber);
		mav.setViewName("fileBoard/updateOk");
	}
}