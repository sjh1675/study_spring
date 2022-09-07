package net.koreate.mvc.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor {
	
	// 서블릿이 호출되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle START");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();	// 디스패쳐 서블릿의 컨트롤러 호출 정보
		System.out.println("controller : " + method.getBean());
		System.out.println("methodObj : " + methodObj);
		System.out.println("전송 방식 : " + request.getMethod());
		
		String command = request.getRequestURI().substring(request.getContextPath().length() + 1);
		System.out.println("요청 : " + command);
		if (command.equals("test1")) {
			response.sendRedirect(request.getContextPath());
			System.out.println("return false");
			return false;
		}
		
		System.out.println("preHandle END");
		// return super.preHandle(request, response, handler);
		return true;
	}

	// 요청이 완료된 후 (출력되기 전)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle START");
		
		System.out.println("viewName : "  + modelAndView.getViewName());
		
		Map<String, Object> map = modelAndView.getModel();
		for (String key : map.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		if(modelAndView.getViewName().equals("another")) {
			modelAndView.setViewName("home");
		}
		
		
		Object result = modelAndView.getModel().get("result");
		
		if (result == null) {
			modelAndView.addObject("result", "postHandle job");
		}
		
		System.out.println("postHandle END");
	}

	
	// 출력이 완료된 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion START");
		System.out.println(request.getAttribute("result"));
		System.out.println(request.getAttribute("result1"));
		
		System.out.println("afterCompletion END");
	}
	
	
	
}
