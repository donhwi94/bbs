package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	// 간단한 쿼리
	@Select("SELECT sysdate FROM dual")
	public String getTime();
}
