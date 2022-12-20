package com.mulcam.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mulcam.myapp.service.MemberService;
import com.mulcam.myapp.vo.MemberVO;

@Controller
public class MemberController {
	@Inject
	MemberService service;
	
	@RequestMapping("/login")
	public String login() {
		return "member/login"; // WEB-INF/views/member/login.jsp
	}
	
	// �α���(DB)
	@RequestMapping(value="/loginOK", method=RequestMethod.POST)
	// public ___ loginOk(String userid, String userpwd) {
	// public ___ loginOk(MemberVO vo, HttpServletRequest request) { 
	public ModelAndView loginOk(MemberVO vo, HttpSession session) { 
		// ��������� Ŭ���� vo�� �Ű������� ����ϸ� ���� ������ ������ ���� �̸��� ���� ���� �ڵ����� request ���ش�.
		MemberVO resultVO = service.login(vo);
		
		// ���̵�, ��й�ȣ�� ������ ���̵�� �̸��� vo��� �����ϰ�,
		// ������ null�� ���ϵȴ�.
		ModelAndView mav = new ModelAndView();
		
		if(resultVO==null) { // �α��� ����
			mav.setViewName("redirect:login");
		}else { // �α��� ����
			// session ���
			// HttpSession session = request.getSession();
			session.setAttribute("logId", resultVO.getUserid());
			session.setAttribute("logName", resultVO.getUsername());
			session.setAttribute("logStatus", "Y");
			// ������
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	// �α׾ƿ�
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		// session ����
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
		
	}
}
