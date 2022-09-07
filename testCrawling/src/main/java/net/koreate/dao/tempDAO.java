package net.koreate.dao;

import org.apache.ibatis.annotations.Insert;

import net.koreate.vo.TempVO;

public interface tempDAO {

	@Insert("INSERT INTO temp_book_tbl VALUES(#{bCode}, #{bName}, #{bAuthor}, #{bPub}, #{bPubdate}, #{bPage}, #{bType}, #{bImage}, #{bOverView}, #{bCount})")
	void insert(TempVO vo) throws Exception;
	
}
