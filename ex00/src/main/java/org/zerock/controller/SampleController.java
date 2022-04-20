package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* 
 * 자동 생성하게 하는 어노테이션
 * @Controller, @Service, @Repository, @Component, @RestController, @Advice
 * WEB-INF/spring/appServlet/servlet-context.xml 설정이 되어있어야 한다. component-scan
 */
@Controller
@RequestMapping("/sample")
public class SampleController {
	private final Logger logger = LoggerFactory.getLogger(SampleController.class.getName());
	// 실행할 메서드
	@RequestMapping("")
	public void basic() {
		logger.info("basic----------------------");
		
	}
}
