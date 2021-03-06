package org.zerock.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// spring-test를 이용해서 테스트
@RunWith(SpringJUnit4ClassRunner.class)
// 테스트할 때 정보 - 생성하고 조립하고 설정 내용을 확인후 테스트 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	// 필요한 객체를 선언 
	// - HikariCP 사용 
	@Setter(onMethod_ = {@Autowired})
	private DataSource dataSource;
	
	// - Mybatis-Spring 사용 : HikariCP 포함(가져다씀)
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlsessionFactory;
	
	// 테스트할 메서드 만들기 - HikariCP
	@Test
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()) {
			log.info("HikariCP : " + conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 테스트할 메서드 만들기 - Mybatis
	@Test
	public void testMybatis() {
		try (SqlSession session = sqlsessionFactory.openSession();
			Connection con = session.getConnection()) {
			log.info("mybatis : " + session);
			log.info("mybatis : " + con);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
