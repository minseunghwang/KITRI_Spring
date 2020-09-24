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
		
		int check = memberDao.memberInsert(memberDto);
		
		mav.addObject("check",check);

		// 회원가입이 완료되었으면! 어디로 이동해라~! 하고 전달
		mav.setViewName("member/registerOk");
		
	}
	@Override
	public void memberIdCheck(ModelAndView mav) {
	}
	@Override
	public void memberZipcode(ModelAndView mav) {
	     
	}
	@Override
	public void memberLoginOk(ModelAndView mav) {
	}
	
	@Override
	public void memberUpdate(ModelAndView mav) {
	}
	@Override
	public void memberUpdateOk(ModelAndView mav) {
	}
	
	@Override
	public void memberDeleteOk(ModelAndView mav) {
	}
}
