package org.zerock.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.board.vo.SampleDTO;
import org.zerock.board.vo.SampleDTOList;

import lombok.extern.log4j.Log4j;

/*
 *  파라미터 전달 테스트 샘플
 */
@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	private final String MODULE = "sample";
	
	/*
	 * 메서드의 전달 파라미터 연습 메서드
	 * 1. ArrayList로 데이터 받기
	 * 	- url : localhost/sample/exList?ids=1&ids=2&ids=3
	 *  request.getParameter()는 한 개만 받아온다 -> 변수
	 *  request.getParameterValues()는 데이터 전부를 받아온다 <- 배열로 선언한 경우 DispatcherSurvlet이 request.getParameterValues()로 받아준다
	 */
	@RequestMapping(value="/exList", method = RequestMethod.GET)
	/*
	 * ArrayList인 경우 @RequestParam("ids") 없으면 받아지지 않는다.
	 * String[] 인 경우 변수 이름만 맞으면 된다. @RequestParam("ids") 없어도 됨
	 */
	public String exList(@RequestParam("ids") ArrayList<String> ids) {
//	public String exList(String[] ids) {
		log.info("ids : " + ids);
		
//		for(String s : ids)
//			log.info("s : " + s);
		
		return "";
	}
	
	/*
	 * 메서드의 전달 파라미터 연습 메서드
	 * 2. DTO로 데이터 받기
	 * 	DTO : Data Transfer Object == VO : Value Object
	 * 	BoardDTO = BoardVO = Board : private 변수 선언, getter&setter, toString()
	 * 	domain package = vo package
	 *  - url : localhost/sample/exDTO?name=lee
	 */
	@RequestMapping(value="/exDTO", method = RequestMethod.GET)
	public String exDTO(SampleDTO dto) {
		log.info("dto : " + dto);
		return "";
	}
	
	/*
	 * 메서드의 전달 파라미터 연습 메서드
	 * 3. DTO를 담은 List로 데이터 받기
	 * 	- url : /localhost/board/exDTOBean?list[0].name=aaa&list[1].name=bbb
	 * 	[, ] - URL로 사용 못하는 문자열 -> 오류 / unicode로 작성해 넘긴다.
	 * 	[ -> %5B
	 * 	] -> %5D
	 * 	=> url : /localhost/board/exDTOBean?list%5B0%5D.name=aaa...
	 */
	@RequestMapping(value="/exDTOBean", method=RequestMethod.GET)
	public String exDTOList(SampleDTOList list) {
		log.info("list : " + list);
		return "";
	}
	
	/*
	 * 메서드의 전달 파라미터 연습 메서드
	 * 4. @ModelAttribute로 데이터 받기
	 * 	- url : localhost/sample/exModelAttr?name=aaa&page=10
	 * 	@ModelAttribute의 경우 Controller에서 request에 담아서 넘겨주는 작업을 하지 않아도 view까지 자동으로 Model에 담아서 데이터를 넘겨준다
	 */
	@RequestMapping(value="/exModelAttr", method=RequestMethod.GET)
	public String exModelAttr(@ModelAttribute("dto") SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return MODULE + "/exModelAttr";
	}
	
	/*
	 * 객체 타입의 데이터를 순수 데이터로 전송 -> JSON 데이터 활용
	 * 순수한 데이터를 전달하는 메서드만 모아서 @RestController를 만든다.
	 * 
	 */
	@RequestMapping(value="/exJSON.json", method=RequestMethod.GET)
	public @ResponseBody SampleDTO exJSON() {
		log.info("exJSON dto data return ...");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("lee");
		
		return dto;
	}
	
}
