package net.koreate.sec.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import net.koreate.sec.security.user.CustomUser;
import net.koreate.sec.vo.ValidationMemberVO;

public class ChatHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> sessionMap = new HashMap<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결됨 : " + session.getId());
		String userName = session.getPrincipal().getName();
		Authentication auth = (Authentication)session.getPrincipal();
		CustomUser customUser = (CustomUser)auth.getPrincipal();
		ValidationMemberVO vo = customUser.getMember();
		System.out.println(vo);
		sessionMap.put(userName, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 시큐리티를 사용하지 않으면 WebSocketSession에 사용자 아이디 정보가 없기 때문에
		// 메세지를 통해 사용자 정보를 넘겨야 한다 (지금은 시큐리티 사용중)
		
		// 클라이언트가 전달한 문자열 정보
		String msg = message.getPayload();
		System.out.println(msg);
		for (WebSocketSession s : sessionMap.values()) {
			s.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결해제됨 : " + session.getId());
		String userName = session.getPrincipal().getName();
		sessionMap.remove(userName);
	}

}
