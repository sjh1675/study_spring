package net.koreate.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrintFilter implements Filter{

	String filterParam;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("PFilter init() 시작");
		
		filterParam = filterConfig.getInitParameter("filterParam");
		
		System.out.println("PFilter init() 종료");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PFilter doFilter() 시작");
		request.setCharacterEncoding(filterParam);
		
		chain.doFilter(request, response);	// 이게 없으면 작업 처리 후 아무런 응답을 보내지 않음
		
		System.out.println("PFilter doFilter() 종료");
	}

	@Override
	public void destroy() {
		System.out.println("PFilter destroy() 시작");
		
		
		System.out.println("PFilter destroy() 종료");
	}	
}