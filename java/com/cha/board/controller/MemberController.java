package com.cha.board.controller;

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

import com.cha.board.model.article.ArticleDB;
import com.cha.board.model.member.Member;
import com.cha.board.model.member.MemberDB;

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
			add(request, response);

		} else if (func.equals("login.do")) {
			login(request, response);
		}
	}

	private void getProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String func = (String) request.getAttribute("func");

		if (func.equals("showLoginForm.do")) {
			forward(request, response, "/member/loginForm.jsp");

		} else if (func.equals("logout.do")) {
			logout(request, response);

		} 
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedUserName");

		response.sendRedirect("/article/list");

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		int idx = db.getMemberIdxByLoginInfo(loginId, loginPw);

		if (idx != 0) {
			// 로그인 처리
			Member member = db.getMemberByIdx(idx);

			HttpSession session = request.getSession();
			session.setAttribute("loginedUserName", member.getNickname());
			session.setAttribute("loginedUserIdx", member.getIdx());

			Cookie popupCookie = new Cookie("popupYn", "true");
			popupCookie.setPath("/");
			popupCookie.setMaxAge(60 * 60 * 24 * 3); // 3일간 쿠키 유지

			response.addCookie(popupCookie);
			response.sendRedirect("/article/list");

		} else {
			// 로그인 실패 처리
			System.out.println("실패");
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String nickname = request.getParameter("nickname");

		db.insertMember(loginId, loginPw, nickname);

		response.sendRedirect("/article/list");

	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path) {

		try {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		} catch (Exception e) {
			System.out.println("포워딩 중 문제 발생");
		}

	}
}
