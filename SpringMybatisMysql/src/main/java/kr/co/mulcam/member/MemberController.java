package kr.co.mulcam.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	// ·Î±×ÀÎÆû get
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "member/login"; // ºäÆÄÀÏ¸í
	}
	// ·Î±×ÀÎ
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		System.out.println("userid->"+userid);
		System.out.println("userpwd->"+userpwd);
		
		ModelAndView mav = new ModelAndView();
		// Model
		mav.addObject("userid",userid);
		mav.addObject("username","¼¼Á¾´ë¿Õ");
		
		// View
		mav.setViewName("home");
		
		return mav;
	}
}
//	/WEB-INF/views/login.jsp