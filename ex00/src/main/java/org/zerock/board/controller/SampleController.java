package org.zerock.board.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
	 */
	@RequestMapping(value="/exJSON.json", method=RequestMethod.GET)
	public @ResponseBody SampleDTO exJSON() {
		log.info("exJSON dto data return ...");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("lee");
		
		return dto;
	}
	
	/*
	 * 처리된 상태코드와 함께 보내는 ResponseEntity 타입
	 */
	@RequestMapping(value="/exEntity", method=RequestMethod.GET)
	public ResponseEntity<String> exEntity() {
		log.info("exEntity status code & data return ...");
		
		String msg = "{'name':'홍길동'}";
		
		// 전달되는 데이터의 정보를 헤더에 담는다. 헤더 정보에 따라 브라우저가 데이터를 표시한다
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	/*
	 * ModelAndView -> 메서드에서 생성해서 데이터를 담은 후 돌려준다.
	 */
	@RequestMapping(value="/exMav", method=RequestMethod.GET)
	public ModelAndView exMav() {
		ModelAndView mav = new ModelAndView();
		
		// 데이터 담기 -> model 대신 사용
		// model.addAttribute("name", "lee"); 와 동일
		mav.addObject("name", "lee");
		
		// jsp 정보 담기
		// return MODULE + "/exMav"; 와 동일
		mav.setViewName(MODULE + "/exMav");
		
		return mav;
	}
	
	// --------------------------------------------------------------------
	/*
	 * file upload Form
	 */
	@RequestMapping(value="/exUpload", method=RequestMethod.GET)
	public void exUpload() {
		log.info("/exUpload.. input Form...");
	}
	
	/*
	 * file upload 처리
	 * form <-> controller : form 태그의 name과 controller parameter의 이름이 같아야 binding이 된다
	 */
	@RequestMapping(value="/exUploadPost", method=RequestMethod.POST)
	public void exUploadPost(ArrayList<MultipartFile> files) {
		for(MultipartFile file : files) {
			if(!file.getOriginalFilename().equals("")) {
				log.info("[upload File List]-------------------");
				log.info("name : " + file.getOriginalFilename());
				log.info("size : " + file.getSize());
			}
		}
	}
	
	
}
