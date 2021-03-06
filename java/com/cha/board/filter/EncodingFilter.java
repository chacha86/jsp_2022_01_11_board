package com.cha.board.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 처리할 코드
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 
		System.out.println("공통코드 실행");
		
		chain.doFilter(request, response);
	}

}
