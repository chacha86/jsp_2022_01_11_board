package com.cha.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/article/*")
public class ArticleController extends HttpServlet {

	ArticleDB db = new ArticleDB();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 

		String uri = request.getRequestURI();
		String[] uriPieces = uri.split("/");

		if (uriPieces.length < 3) {
			System.out.println("잘못된 요청입니다.");
			return;
		}

		String func = uriPieces[2];

		if (func.equals("add")) {

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			String nickname = request.getParameter("nickname");

			// 주소록 추가
			db.insertArticle(title, body, nickname);
			list(request, response);

		} else if (func.equals("list")) {
			list(request, response);

		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Article> articleList = db.getArticleList();
		request.setAttribute("articleList", articleList);

		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
	}

}
