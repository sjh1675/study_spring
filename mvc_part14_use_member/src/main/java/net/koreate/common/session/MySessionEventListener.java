package net.koreate.common.session;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import net.koreate.user.vo.UserVO;

// HttpSession => session 정보를 저장하는 객체
// HttpSessionEvent => HttpSession 객체가 생성/제거 될 때 발생하는 이벤트

// 이 클래스에서는 이벤트를 처리한다


// HttpSessionAttributesListener : 속성값이 추가, 수정, 삭제가 되었을 때 발생하는
// HttpSessionBindingEvent 처리를 하는 객체

@Component
public class MySessionEventListener implements HttpSessionListener, HttpSessionAttributeListener {
	
	public static Hashtable<String, HttpSession> sessionRepository;
	
	public MySessionEventListener() {
		if (sessionRepository == null) {
			sessionRepository = new Hashtable<>();
		}
	}
	
	public boolean expireDuplicatedSession(String uid, String sessionId) {
		System.out.println("Active Session count : " + sessionRepository.size());
//		Set<String> keySet = sessionRepository.keySet();
		
		for (HttpSession s : sessionRepository.values()) {
			UserVO vo = (UserVO)s.getAttribute("userInfo");
			
			if (vo != null && vo.getUid().equals(uid)) {
				// 중복 로그인 요청
				if (!s.getId().equals(sessionId)) {
					// session에 등록된 session 아이디 값과 현재 로그인 요청이 들어온 session의 아이디 값이 틀리면 서로 다른 브라우저에서 로그인 요청이 들어왔음
					System.out.printf("중복 로그인 user %s, sessionId %s %n", vo.getUid(), sessionId);
//					s.removeAttribute("userInfo");
					s.setAttribute("invalidate", "중복 로그인으로 인해 로그아웃 됩니다.");
					return true;
				}
			}			
		}		
		return false;
	}
	
	// 객체 생성 시
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.printf("생성된 SESSION ID %s %n", se.getSession().getId());
	}

	// 객체 삭제 시
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("session destroyed : " + session.getId());
		sessionRepository.remove(session.getId());
	}

	// 속성 값 추가 시
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded 호출");
		System.out.printf("SESSION ID : %s %n", event.getSession().getId());
		System.out.printf("SESSION 추가 된 attribute name : %s : value : %s %n", event.getName(), event.getValue());
				
		if (event.getName().equals("userInfo")) {
			HttpSession session = event.getSession();
			sessionRepository.put(session.getId(), session);
		}
		
		System.out.println("attributeAdded 종료");
	}

	// 속성 값 삭제 시
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("attributeRemoved 호출");
		System.out.printf("SESSION 삭제 된 attribute name : %s : value : %s %n", event.getName(), event.getValue());
		
		HttpSession session = event.getSession();
		if (event.getName().equals("userInfo")) {
			sessionRepository.remove(session.getId());
		}
		
		System.out.println("attributeRemoved 종료");
	}

	// session.setAttribute 기존 값이 이미 존재하는 값일 때	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded 호출");
		System.out.printf("SESSION ID : %s %n", event.getSession().getId());
		System.out.printf("SESSION 수정 된 attribute name : %s : value : %s %n", event.getName(), event.getValue());
				
		if (event.getName().equals("userInfo")) {
			HttpSession session = event.getSession();
			sessionRepository.put(session.getId(), session);
		}
		
		System.out.println("attributeAdded 종료");
	}
	
}
