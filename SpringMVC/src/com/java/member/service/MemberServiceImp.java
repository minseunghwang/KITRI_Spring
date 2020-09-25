package com.java.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

public class MemberServiceImp implements MemberService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public MemberServiceImp() {}
	public MemberServiceImp(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void memberRegisterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto) map.get("memberDto");
		memberDto.setMemberLevel("BB");
		
		int check = memberDao.memberInsert(memberDto);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		mav.setViewName("member/registerOk");
		
		
	}
	@Override
	public void memberIdCheck(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		HAspect.logger.info(HAspect.logMsg+id);
		int check=memberDao.memberIdCheck(id);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		mav.addObject("id", id);
		mav.setViewName("member/idCheck");
		
	}
	@Override
	public void memberZipcode(ModelAndView mav) {
	     
	}
	@Override
	public void memberLoginOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		Map<String, String> hmap=new HashMap<String, String>();
		hmap.put("id", request.getParameter("id"));
		hmap.put("pw", request.getParameter("pw"));
		
		HAspect.logger.info(HAspect.logMsg+hmap.toString());
		String value=memberDao.memberLoginOk(hmap);
		HAspect.logger.info(HAspect.logMsg+value);
		
		mav.addObject("memberLevel", value);
		mav.addObject("id", hmap.get("id"));
		mav.setViewName("member/loginOk");
		
		
	}
	
	@Override
	public void memberUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		MemberDto memberDto = memberDao.memberUpdate(id);
		HAspect.logger.info(HAspect.logMsg + memberDto);
		
		mav.addObject("memberDto", memberDto);
		mav.setViewName("member/update");
	}
	@Override
	public void memberUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto) map.get("memberDto");
		int check = memberDao.memberUpdateOk(memberDto);
		HAspect.logger.info(HAspect.logMsg + check);
		HAspect.logger.info(HAspect.logMsg + memberDto);
		mav.addObject("check", check);
		HAspect.logger.info(HAspect.logMsg + mav.getModel());
		HAspect.logger.info(HAspect.logMsg + mav.getView());
		mav.setViewName("member/updateOk");
	}
	
	@Override
	public void memberDeleteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put("id", request.getParameter("id"));
		hmap.put("pw", request.getParameter("pw"));
		HAspect.logger.info(HAspect.logMsg + hmap);
		
		int check = memberDao.memberDeleteOk(hmap);
		
		mav.addObject("check", check);
		mav.setViewName("member/deleteOk");
	}
}
