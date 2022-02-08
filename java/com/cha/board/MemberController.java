package com.cha.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	MemberDB db = new MemberDB();
	ArticleDB adb = new ArticleDB();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String[] uriPieces = uri.split("/");

		if (uriPieces.length < 3) {
			System.out.println("잘못된 요청입니다.");
			return;
		}

		String func = uriPieces[2];
		String method = req.getMethod(); // POST, GET

		req.setAttribute("func", func);

		if (method.equals("POST")) {
			postProcess(req, resp);

		} else if (method.equals("GET")) {
			getProcess(req, resp);
		}

	}

	private void postProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("add.do")) {
			//
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String nickname = request.getParameter("nickname");
			
			db.insertMember(loginId, loginPw, nickname);
			
			response.sendRedirect("/article/list");
			
		} else if(func.equals("login.do")) {
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			
			int idx = db.getMemberIdxByLoginInfo(loginId, loginPw);
			
			if(idx != 0) {
				// 로그인 처리
				Member member = db.getMemberByIdx(idx);
				//ArrayList<Article> articles = adb.getAllArticles();
				
				// request는 데이터 유지가 힘들다.
				//request.setAttribute("loginedUserName", member.getNickname());
				//request.setAttribute("articleList", articles);
				
				// session 저장소에 저장하도록 한다.
				HttpSession session = request.getSession();
				session.setAttribute("loginedUserName", member.getNickname());
				session.setAttribute("loginedUserIdx", member.getIdx());
				//session.setAttribute("articleList", articles);
				
				//ServletContext application = request.getServletContext();
				//application.setAttribute("loginedUserName", member.getNickname());
				//application.setAttribute("articleList", articles);
				
				//forward(request, response, "/list.jsp");
				
				// 쿠키 추가
				// 쿠키 기본 패스 => /member/login.do
				Cookie popupCookie = new Cookie("popupYn", "true");
				
				// 쿠키 옵션
				// 1. path				
				popupCookie.setPath("/");
				
				// 2. 만료 날짜
				popupCookie.setMaxAge(60 * 60 * 24 * 3); // 3일간 쿠키 유지				
				
				// 3. 도메인 -> m.naver.com, news.naver.com, naver.com ...
				// popupCookie.setDomain("m.naver.com");
				
				response.addCookie(popupCookie);
				response.sendRedirect("/article/list");
				
				// 게시물 목록으로 간다. -> forwading?? redirecting??
				
			} else {
				// 로그인 실패 처리
				System.out.println("실패");
			}
		} 
	}

	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");
		
		if(func.equals("showLoginForm.do")) {
			forward(request, response, "/member/loginForm.jsp");
			
		} else if(func.equals("logout.do")) {
			// 로그아웃 처리
			// session 내용을 지운다.
			HttpSession session = request.getSession();
			session.removeAttribute("loginedUserName");
			
			response.sendRedirect("/article/list");
			
		} else if(func.equals("test.do")) {
			request.setAttribute("test", "req");
			
			HttpSession session = request.getSession();			
			session.setAttribute("test", "sess");
			
			ServletContext application = request.getServletContext();
			application.setAttribute("test", "app");
			
			forward(request, response, "/test.jsp");
			
			
		}
	}
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		
		try {			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		} catch(Exception e) {
			System.out.println("포워딩 중 문제 발생");
		}
			
	}
}
