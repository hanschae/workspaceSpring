package com.mulcam.myapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mulcam.myapp.service.BoardService;
import com.mulcam.myapp.vo.BoardVO;

@Controller
public class BoardController {
	@Inject
	BoardService service;
	
	// �۸��
	@RequestMapping("/board/list")
	public ModelAndView boardList() {
		
		ModelAndView mav = new ModelAndView();
		
		// DB��ȸ
		//List<BoardVO> list = service.boardList();
		//mav.addObject("list",list); // list=list
		
		mav.addObject("list", service.boardList());
		
		mav.setViewName("board/boardList");
		
		return mav;
	}
	
	// �۾��� ��
	@RequestMapping("/board/write")
	public String boardWrite() {
		return "board/boardWrite";
	}
	// �۾��� DB
	@RequestMapping(value="/board/writeOk", method=RequestMethod.POST)
	public ModelAndView boardWriteok(BoardVO vo, HttpServletRequest request) {
		// BoardVO���� ������ subject, contents�� request��ü�� ���� getParameter()�� �������� �������ش�.
		
		vo.setIp(request.getRemoteAddr()); // �������� ip�� ���Ͽ� vo�� ������ setting
		
		// vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("logId");
		vo.setUserid(userid);
		
		int cnt = service.boardWriteOk(vo);
		
		ModelAndView mav = new ModelAndView();
		// ����� �� ��� > �Խ��� ������� �̵�
		if(cnt>0) {
			mav.setViewName("redirect:/board/list");
		}else { // ����� �ȵȰ�� > �۾��� ������ �̵�
			mav.addObject("msg", "�۵��");
			mav.setViewName("board/boardResult");
		}
		return mav;
	}
	// �۳��뺸��
	@RequestMapping("/board/view")
	public String boardView(int no, Model model) {		
		// ��ȸ�� ����
		service.hitCount(no);
		
		// �ۼ���
		BoardVO vo = service.boardView(no);
		model.addAttribute("viewVO", vo);
		return "board/boardView";
	}
	
	// �� ���� ��
	@RequestMapping("/board/edit")
	public ModelAndView boardEdit(int no) {
		BoardVO vo = service.boardView(no);
		
		ModelAndView mav = new ModelAndView();
		// �����ϱ� ���� ���ڵ�����
		mav.addObject("vo",vo);
		mav.setViewName("board/boardEdit");
		
		return mav;
	}
	
	// �ۼ��� DB
	@RequestMapping(value="/board/editOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession session) {
		
		// �α��� ���̵� ��� vo�� userid�� ����
		vo.setUserid((String)session.getAttribute("logId"));
		
		// vo -> no, subject, content, userid
		int cnt = service.boardEditOk(vo);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0) { // ���� ������ ��� �۳��뺸��� �̵�
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:/board/view");
		}else {
			mav.addObject("msg", "�ۼ���");
			mav.setViewName("board/boardResult");
		}
		return mav;
	}
	// �� �����
	@RequestMapping("/board/del")
	public ModelAndView boardDel(int no, HttpServletRequest request) {
		String userid = (String)request.getSession().getAttribute("logId");
		
		int cnt = service.boardDel(no, userid);
		
		ModelAndView mav = new ModelAndView();
		if(cnt>0){ // ���� > ���
			mav.setViewName("redirect:/board/list");
		}else { // �������� > �۳��뺸��
			mav.addObject("no", no);
			mav.setViewName("redirect:/board/view");
		}
		return mav;
	}
}
