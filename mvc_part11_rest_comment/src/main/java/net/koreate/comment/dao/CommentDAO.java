package net.koreate.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.comment.vo.CommentDTO;
import net.koreate.common.utils.Criteria;

public interface CommentDAO {
	
	@Select("SELECT * FROM tbl_comment "
			+ " WHERE bno = #{bno} "
			+ " ORDER BY cno DESC")
	List<CommentDTO> commentList(int bno)throws Exception;
	
	@Insert("INSERT INTO tbl_comment(bno,commentText,commentAuth) "
			+"VALUES(#{bno},#{commentText},#{commentAuth})")
	int add(CommentDTO dto)throws Exception;
	
	@Update("UPDATE tbl_comment SET "
		  + " commentAuth = #{commentAuth} , "
		  + " commentText = #{commentText} , "
		  + " updatedate = now() "
		  + " WHERE cno = #{cno} ")
	int update(CommentDTO dto) throws Exception;
	
	@Delete("DELETE FROM tbl_comment WHERE cno = #{cno}")
	int delete(int cno) throws Exception;
	
	@Select("SELECT * FROM tbl_comment WHERE bno = #{bno} ORDER BY cno DESC limit #{cri.startRow}, #{cri.perPageNum}")
	List<CommentDTO> listPage(
				@Param("bno") int bno,
				@Param("cri") Criteria cri)throws Exception;	// 전달해야 할 값을 하나만 받을 수 있는데 처음부터 map 객체 하나로 받거나 @Param 어노테이션을 지정하여 map객체의 키값으로 저장하여 전달한다
	
	@Select("SELECT count(*) FROM tbl_comment WHERE bno = #{bno}")
	int totalCount(int bno)throws Exception;
}













