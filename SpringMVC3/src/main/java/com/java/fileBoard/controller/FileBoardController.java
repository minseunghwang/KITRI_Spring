package com.java.fileBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.fileBoard.dto.FileBoardDto;
import com.java.fileBoard.service.FileBoardService;

@Controller
public class FileBoardController{
	@Autowired
	private FileBoardService fileBoardService;
	
	
	// 기본 생성자 & setter 삭제
	
	
	@RequestMapping(value="/fileBoard/write.do")
	public ModelAndView fileBoardWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardWrite(mav);
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/writeOk.do")
	public ModelAndView fileBoardWriteOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("fileBoardDto",fileBoardDto);
		fileBoardService.fileBoardWriteOk(mav);
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/list.do")
	public ModelAndView fileBoardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardList(mav);
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/read.do")
	public ModelAndView fileBoardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardRead(mav);
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/download.do")
	public void fileBoardDownLoad(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response",response);
		fileBoardService.fileBoardDownLoad(mav);
	}
	
	public ModelAndView fileBoardDelete(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public ModelAndView fileBoardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	@RequestMapping(value="/fileBoard/update.do")
	public ModelAndView fileBoardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		fileBoardService.fileBoardUpdate(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/fileBoard/updateOk.do")
	public ModelAndView fileBoardUpdateOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("fileBoardDto",fileBoardDto);
		fileBoardService.fileBoardUpdateOk(mav);
		return mav;
	}
}
