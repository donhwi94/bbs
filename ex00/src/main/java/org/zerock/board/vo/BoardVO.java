package org.zerock.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	// 글번호, 제목, 내용, 작성자, 작성일, 조회수
	private Long no;
	private String title;
	private String contents;
	private String writer;
	private Date writeDate;
	private Long hit;
	
	// getter / setter / toString / 생성자 -> lombok이 해결해 준다. compile 할 때 만들어줌
	
}
