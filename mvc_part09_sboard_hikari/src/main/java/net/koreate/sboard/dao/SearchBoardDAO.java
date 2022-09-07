package net.koreate.sboard.dao;

import java.util.List;

import net.koreate.common.utils.SearchCriteria;
import net.koreate.sboard.vo.SearchBoardVO;

public interface SearchBoardDAO {
	
	// 게시물 등록
	int create(SearchBoardVO vo) throws Exception;
	
	// 게시글 수정
	int update(SearchBoardVO vo) throws Exception;
	
	// 게시글 삭제
	int remove(int bno) throws Exception;	
	
	// 게시글 상세보기
	SearchBoardVO read(int bno) throws Exception;
	
	// 조회수 증가
	void updateViewCnt(int bno) throws Exception;
	
	// 게시물 목록
	List<SearchBoardVO> searchList(SearchCriteria cri) throws Exception;
	
	// 검색 된 게시물의 전체 개수 SearchCriteria cri 매개변수에서 뺐음
	int searchListCount() throws Exception;
	
}
