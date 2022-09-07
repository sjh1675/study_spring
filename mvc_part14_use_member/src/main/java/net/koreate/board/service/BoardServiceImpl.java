package net.koreate.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.koreate.board.dao.AttachmentDAO;
import net.koreate.board.dao.BoardDAO;
import net.koreate.board.vo.BoardVO;
import net.koreate.common.utils.PageMaker;
import net.koreate.common.utils.SearchCriteria;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardDAO dao;
	private final AttachmentDAO attachDAO;
	
	@Override
	public void register(BoardVO vo) throws Exception {
		// INSERT title, content, uno
		dao.regist(vo);
		// origin column 수정
		dao.updateOrigin();		
		// 업로드된 파일 정보 저장
		List<String> files = vo.getFiles();
		if (files == null) {
			return;
		}
		for (String fullName : files) {
			attachDAO.addAttachment(fullName);
		}
	}
	
	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO vo = dao.read(bno);
		List<String> fileList = attachDAO.getAttach(bno);
		vo.setFiles(fileList);
		return vo;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public void replyRegister(BoardVO vo) throws Exception {
		// 기존 글들의 seq 정렬값을 한칸씩 밀어야 됨
		// 원본 글 값 보다 큰값이 존재하면 각 게시글 마다 seq 값 1씩 증가
		dao.updateReply(vo);
		vo.setSeq(vo.getSeq() + 1);
		vo.setDepth(vo.getDepth() + 1);
		dao.replyRegister(vo);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.modify(vo);
		attachDAO.deleteAttach(vo.getBno());
		List<String> fileList = vo.getFiles();
		if (fileList == null || fileList.isEmpty()) {
			return;
		}
		for (String name : fileList) {
			attachDAO.replaceAttachment(vo.getBno(), name);
		}		
	}

	@Override
	public void remove(int bno) throws Exception {
		// 첨부파일 경로 table에서 삭제
		attachDAO.deleteAttach(bno);
		
		// 게시글 삭제
		dao.remove(bno);
		
	}

	@Override
	public List<BoardVO> listReply(SearchCriteria scri) throws Exception {
		List<BoardVO> list = dao.listReply(scri);
		return list;
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria scri) throws Exception {
		int totalCount = dao.listCount(scri);
		PageMaker pm = new PageMaker();
		pm.setCri(scri);
		pm.setDisplayPageNum(7);
		pm.setTotalCount(totalCount);
		return pm;
	}

}
