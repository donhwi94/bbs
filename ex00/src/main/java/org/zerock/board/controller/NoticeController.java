package org.zerock.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.board.vo.NoticeDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice")
@Log4j
public class NoticeController {
	
	/*
	 * 날짜 형식 입력 테스트
	 * 맵핑 - get 방식. /notice/write?no=10&startDate=2022-04-28
	 * url의 날짜 형식을 입력받기 위해 NoticeDTO에서 @DateTimeFormat(pattern="yyyy-MM-dd") 선언이 필요
	 */
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void write(NoticeDTO dto) {
		log.info("dto : " + dto);
	}
}
