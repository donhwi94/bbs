package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * 자동 생성하게하는 어노테이션
 * @Controller, @Service, @Repository, @Component, @RestController, @Advice
 *  /WEB-INF/spring/root-context.xml에 설정이 되어 있어야 한다. component-scan
 * @Controller의 경우 root-context.xml이 아닌 /WEB-INF/spring/appServlet/servlet-context.xml에 설정해야함
 * @Component : 객체를 생성하기 위한 구성요소
 */
@Component
@Data
public class Chef {
	
}
