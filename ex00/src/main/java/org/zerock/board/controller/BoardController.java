package org.zerock.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.board.service.BoardService;

import lombok.Setter;

@Controller
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class.getName());
	
	private final String MODULE = "board";
	
	// @Setter - lombok 사용, @Autowired - Spring 사용
	// 대신 사용 가능한 어노테이션 : @Autowired - Spring, @Inject - java
	// DI 적용 시 BoardService 타입 : 1. BoardService 인터페이스  2. BoardServiceImpl
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	/*
	 * 실행할 메서드 - 리스트
	 * 맵핑 - get 방식. list.do
	 */
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String list() throws Exception {
		logger.info("list() - 게시판 리스트 --------------");
		
		service.list();
		
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
	public String write() {
		logger.info("write() - 게시판 글쓰기 처리 -----------------");
		return "redirect:list.do";
	}
	
	/*
	 * 실행할 메서드 - 글보기
	 * 맵핑 - get 방식. view.do
	 */
	@RequestMapping(value="/view.do", method=RequestMethod.GET)
	public String view() {
		logger.info("view() - 게시판 글 보기 --------------");
		return MODULE + "/view";
	}
	
	/*
	 * 실행할 메서드 - 글수정 폼
	 * 맵핑 - get 방식. update.do
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String updateForm() {
		logger.info("updateForm() - 게시판 글수정 폼 -----------------");
		return MODULE + "/update";
	}
	
	/*
	 * 실행할 메서드 - 글수정 처리
	 * 맵핑 - post 방식. update.do
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String update() {
		logger.info("update() - 게시판 글수정 처리 -----------------");
		return "redirect:view.do";
	}
	
	/*
	 * 실행할 메서드 - 글삭제 처리
	 * 맵핑 - post 방식. delete.do
	 */
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String delete() {
		logger.info("delete() - 게시판 글삭제 처리 -----------------");
		return "redirect:list.do";
	}
}
