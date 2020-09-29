package com.java.fileBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.fileBoard.dto.FileBoardDto;
import com.java.fileBoard.service.FileBoardService;

public class FileBoardController extends MultiActionController {
	private FileBoardService fileBoardService;

	public void setFileBoardService(FileBoardService fileBoardService) {
		this.fileBoardService = fileBoardService;
	}
	public FileBoardController() {	}
	public FileBoardController(FileBoardService fileBoardService) {
		this.fileBoardService = fileBoardService;
	}

	public ModelAndView fileBoardWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardWrite(mav);
		return mav;
	}
	
	public ModelAndView fileBoardWriteOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("fileBoardDto",fileBoardDto);
		fileBoardService.fileBoardWriteOk(mav);
		return mav;
	}
	
	public ModelAndView fileBoardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardList(mav);
		return mav;
	}
	public ModelAndView fileBoardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		fileBoardService.fileBoardRead(mav);
		return mav;
	}
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
	public ModelAndView fileBoardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		fileBoardService.fileBoardUpdate(mav);
		
		return mav;
	}
	public ModelAndView fileBoardUpdateOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("fileBoardDto",fileBoardDto);
		fileBoardService.fileBoardUpdateOk(mav);
		return mav;
	}
}
