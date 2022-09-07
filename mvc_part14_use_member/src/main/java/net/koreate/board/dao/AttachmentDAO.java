package net.koreate.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.koreate.board.provider.BoardQueryProvider;

public interface AttachmentDAO {

	// 첨부파일 등록
	@Insert("INSERT INTO tbl_attach(fullName, bno) VALUES(#{fullName}, LAST_INSERT_ID())")
	void addAttachment(String fullName) throws Exception;

	@Select("SELECT fullName FROM tbl_attach WHERE bno = #{bno}")
	List<String> getAttach(int bno) throws Exception;

	// 게시글 삭제 시 첨부파일 테이블에 저장된 파일경로 삭제
	@DeleteProvider(type=BoardQueryProvider.class, method="deleteAttach")
	
	void deleteAttach(int bno) throws Exception;
	
	// 첨부파일 수정
	@Insert("INSERT INTO tbl_attach(bno, fullName) VALUES(#{bno}, #{fullName})")
	void replaceAttachment(@Param("bno")int bno, @Param("fullName")String name) throws Exception;	// 마이바티스에 단 하나의 매개변수만 전달 할 수 있음(지금 2개여서 안됨)
																									// @Param("키값") 어노테이션으로 키값을 지정하여 자동으로 맵 객체로 전달해준다
	@Select("SELECT fullName FROM  tbl_attach WHERE DATE_FORMAT(regdate, '%Y-%m-%d') = DATE_SUB(CURDATE(), interval 1 day)")
	List<String> getTrashAttach() throws Exception;
}
