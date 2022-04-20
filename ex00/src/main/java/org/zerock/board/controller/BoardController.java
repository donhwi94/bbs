package org.zerock.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class.getName());
	
	/*
	 * 실행할 메서드 - 리스트
	 * 맵핑 - get 방식. list.do
	 */
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String list() {
		logger.info("list() - 게시판 리스트 --------------");
		return "board/list";
	}
	
	/*
	 * 실행할 메서드 - 글쓰기 폼
	 * 맵핑 - get 방식. write.do
	 */
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String writeForm() {
		logger.info("writeForm() - 게시판 글쓰기 폼 -----------------");
		return "board/write";
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
	
}
