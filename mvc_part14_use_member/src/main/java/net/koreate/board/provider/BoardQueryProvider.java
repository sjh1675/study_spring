package net.koreate.board.provider;

import org.apache.ibatis.jdbc.SQL;

import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.SearchCriteria;

public class BoardQueryProvider {
	
	// remove: (showboard = n으로 update)
	
	public String remove(int bno) {
		return new SQL().UPDATE("re_tbl_board").SET("showboard = 'n'").WHERE("bno = #{bno}").toString();
	}
	
	// tbl_attach 테이블의 첨부파일 경로 삭제 (원래는 AttachQueryProvider 클래스를 작성하여 해야 하는데 딱 한개뿐이라 귀찮아서 생략)
	public String deleteAttach(int bno) {
		SQL sql = new SQL();
		sql.DELETE_FROM("tbl_attach");
		sql.WHERE("bno = #{bno}");
		return sql.toString();
	}
	

	public String register(BoardVO board) {
		// 원본글 등록		
		SQL sql = new SQL();
		
		sql.INSERT_INTO("re_tbl_board"); // board(col1, col2, ..) 로 작성해도 된다
		sql.INTO_COLUMNS("title", "content");
		if (board.getOrigin() != 0) {	// 0이 아니면 답변글
			sql.INTO_COLUMNS("origin", "depth", "seq");
		}
		sql.INTO_COLUMNS("uno");
		
		sql.INTO_VALUES("#{title}", "#{content}");		
		if (board.getOrigin() != 0) {	// 0이 아니면 답변글
			sql.INTO_VALUES("#{origin}, #{depth}, #{seq}");
		}
		sql.INTO_VALUES("#{uno}");		
		
		String query = sql.toString();
		System.out.println("register query : " + query);
		return query;
		
	}
	
	public String searchSelectSql(SearchCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("R.*, U.uname AS writer");
		sql.FROM("re_tbl_board AS R NATURAL JOIN tbl_user AS U");
		getSearchWhere(cri, sql);
		sql.ORDER_BY("R.origin DESC, R.seq ASC");
		sql.LIMIT(cri.getPerPageNum());
		sql.OFFSET(cri.getStartRow());	// 검색 시작 인덱스
		String query = sql.toString();
		System.out.println(query);
		return query;
	}
	
	// 검색된 게시글의 개수
	public String searchSelectCount(SearchCriteria cri) {
		SQL sql = new SQL();
		sql.SELECT("count(*)");
		sql.FROM("re_tbl_board AS R NATURAL JOIN tbl_user AS U");
		getSearchWhere(cri, sql);
		String query = sql.toString();
		System.out.println(query);
		return query;
	}
	
	
	
	
	// 검색 조건에 따라 다른 sql을 사용
	private void getSearchWhere(SearchCriteria cri, SQL sql) {
		String titleQuery = "title LIKE CONCAT('%', '" + cri.getKeyword() + "', '%')";
		String contentQuery = "content LIKE CONCAT('%', #{keyword}, '%')";
		String writerQuery = "U.uname LIKE CONCAT('%', #{keyword}, '%')";
		
		if (cri.getSearchType() != null && !cri.getSearchType().trim().equals("") && !cri.getSearchType().trim().equals("n")) {
			
			if (cri.getSearchType().contains("t")) sql.WHERE(titleQuery);		// 여기 OR이든 AND든 붙어도 최초의 WHERE 메소드 수행 시 
																				// 제일 앞에 WHERE이 붙음 >> 정상작동
			if (cri.getSearchType().contains("c")) sql.OR().WHERE(contentQuery); // 만약 OR이 없이 WHERE이 여러개이면 AND 연산자로 인식
			if (cri.getSearchType().contains("w")) sql.OR().WHERE(writerQuery);
		}
	}
		
}
