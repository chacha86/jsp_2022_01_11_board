package com.cha.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	MemberDB db = new MemberDB();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		System.out.println("공통코드 실행");

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
				System.out.println("성공");
			} else {
				// 로그인 실패 처리
				System.out.println("실패");
			}
			
			
		}
	}

	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

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
