package org.zerock.board.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/*
 * 메서드의 전달 파라미더 유형 테스트를 위한 DTO
 */
@Data // setter & getter & toString & 생성자를 자동으로 만드는 어노테이션
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<SampleDTO>();
	}
}
