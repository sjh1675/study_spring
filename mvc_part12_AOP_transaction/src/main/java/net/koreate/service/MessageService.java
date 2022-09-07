package net.koreate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.koreate.dao.MessageDAO;
import net.koreate.dao.UserDAO;
import net.koreate.vo.MessageVO;
import net.koreate.vo.UserVO;

@Service
@RequiredArgsConstructor
public class MessageService {
	
	private final UserDAO userDAO;
	private final MessageDAO messageDAO;
	
					// 메소드가 수행되기 전에 커넥션 객체의 오토커밋을 false로 만들고
					// 내부에 호출된 모든 메소드가 정상적으로 수행되면 커밋시켜서 하나의 트랜잭션을 이루게 만든다	
	@Transactional	// 이 어노테이션을 사용하기 위해 설정이 필요함 : root-context.xml의 트랜잭션매니저(기능) + tx:annotation-driven이 어노테이션을 찾음
	public void addMessage(MessageVO vo) throws Exception{
		System.out.println("addMessage Service 호출");
		// 발신자 포인트 증가
		UserVO uv = new UserVO();
		uv.setUid(vo.getSender());
		uv.setUpoint(10);
		userDAO.updatePoint(uv);
		// 메세지 등록
		messageDAO.create(vo);
		System.out.println("addMessage Service 종료");
	}

	public List<MessageVO> list() throws Exception {
		
		return messageDAO.list();
	}

	public MessageVO readMessage(String uid, int mno) throws Exception {
/*
		MessageVO message = messageDAO.readMessage(mno); if (message.getOpendate() !=
		null) { throw new NullPointerException("이미 읽은 메세지입니다."); }
*/
		
		// 여기는 읽지 않은 메세지(한번이라도 개봉되지 않은)이므로 읽은 시간을 갱신한다
		messageDAO.updateMessage(mno);
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setUpoint(5);
		userDAO.updatePoint(vo);
		
		MessageVO message = messageDAO.readMessage(mno);
		return message;
	}
	
}
















