package com.cha.board;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/article/detail", "/article/showAddForm"})
public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//chain.doFilter(request, response);
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// 로그인 했는가?
		// 로그인했으면 그냥 고
		// 안했으면 로그인 페이지로 
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedUserName") == null) {
			res.sendRedirect("/member/showLoginForm.do");
			return;
		}
		
		chain.doFilter(req, res);
		
	}


}
