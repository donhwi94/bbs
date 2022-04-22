package org.zerock.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// 객체 생성이나 DI 적용이 src/main/java -> 자동 적용되나, src/test/java -> 자동 적용 안됨(읽어올 파일 지정)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// log 객체 사용하기 위한 지정. log - 사용 내역 출력
@Log4j
public class SampleTest {
	
}
