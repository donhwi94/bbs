package org.zerock.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {
	
	/*
	 * 메인 표시 화면
	 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public void main() {
		
	}
	
	/*
	 * 로그인 메소드
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpSession session) {
		
		// session에 id가 있으면 로그인 한 것 - 강제 로그인
		session.setAttribute("id", "test");
		log.info("로그인 처리 됨");
		
		return "redirect:main";
	}
	
	/*
	 * 로그아웃 메소드
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		/*
		 * 로그아웃 처리 
		 * 1. 세션을 지우거나 : session.invalidate()
		 * 2. 로그인 체크하는 속성값만 지우거나(id) : session.removeAttribute("id") -> 해당 세션을 계속 써야할 때 사용한다
		 */
		session.removeAttribute("id");
		log.info("로그아웃 처리 됨");
		
		return "redirect:main";
	}
}
