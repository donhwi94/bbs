package org.zerock.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.board.service.BoardServiceImpl;
import org.zerock.board.vo.BoardVO;

import lombok.Setter;


/* 
 * 자동 생성하게 하는 어노테이션
 * @Controller, @Service, @Repository, @Component, @RestController, @Advice
 * WEB-INF/spring/appServlet/servlet-context.xml 설정이 되어있어야 한다. component-scan
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class.getName());
	
	private final String MODULE = "board";
	
	// @Setter - lombok 사용, @Autowired - Spring 사용
	// 대신 사용 가능한 어노테이션 : @Autowired - Spring, @Inject - java
	// DI 적용 시 BoardService 타입 : 1. BoardService 인터페이스  2. BoardServiceImpl
	@Setter(onMethod_ = @Autowired)
	private BoardServiceImpl service;
	
	/*
	 * 실행할 메서드 - 리스트
	 * 맵핑 - get 방식. list.do
	 */
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("list() - 게시판 리스트 --------------");
		
		// model에 데이터를 담으면 model안에 있는 request에 데이터가 담긴다.
		model.addAttribute("list", service.list());
		
		return MODULE + "/list";
	}
	
	/*
	 * 실행할 메서드 - 글쓰기 폼
	 * 맵핑 - get 방식. write.do
	 */
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String writeForm() {
		logger.info("writeForm() - 게시판 글쓰기 폼 -----------------");
		return MODULE + "/write";
	}
	
	/*
	 * 실행할 메서드 - 글쓰기 처리
	 * 맵핑 - post 방식. write.do
	 */
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(BoardVO vo) throws Exception {
		logger.info("write().vo : " + vo + " - 게시판 글쓰기 처리 -----------------");
		
		service.write(vo);
		return "redirect:list.do";
	}
	
	/*
	 * 실행할 메서드 - 글보기
	 * 맵핑 - get 방식. view.do
	 * get 방식으로 넘어오는 데이터 받기 : url -> localhost/board/view.do?no=1&... -> data : DispatcherSurvlet이 문자열로 받아서 넘겨준다
	 * -> 함수에서는 @RequestParam("key") int value로 받는다 -> key와 value명이 같으면 @RequestParam은 생략 가능
	 */
	@RequestMapping(value="/view.do", method=RequestMethod.GET)
	public String view(Model model, @RequestParam("no") Long no) throws Exception {
		logger.info("view().no : " + no + " - 게시판 글 보기 --------------");
		
		model.addAttribute("vo", service.view(no));
		
		// /WEB-INF/view/ + board/view + .jsp - servlet-context.xml
		return MODULE + "/view";
	}
	
	/*
	 * 실행할 메서드 - 글수정 폼
	 * 맵핑 - get 방식. update.do
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String updateForm(Model model, @RequestParam("no") Long no) throws Exception {
		logger.info("updateForm() - 게시판 글수정 폼 -----------------");
		
		model.addAttribute("vo", service.view(no));
		return MODULE + "/update";
	}
	
	/*
	 * 실행할 메서드 - 글수정 처리
	 * 맵핑 - post 방식. update.do
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String update(BoardVO vo) throws Exception {
		logger.info("update().vo : " + vo + " - 게시판 글수정 처리 -----------------");
		
		service.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo();
	}
	
	/*
	 * 실행할 메서드 - 글삭제 처리
	 * 맵핑 - post 방식. delete.do
	 */
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String delete(@RequestParam("no") Long no) throws Exception {
		logger.info("delete().no : " + no + " - 게시판 글삭제 처리 -----------------");
		
		service.delete(no);
		
		return "redirect:list.do";
	}
	
}
