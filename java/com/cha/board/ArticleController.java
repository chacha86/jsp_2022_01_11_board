package com.cha.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/article/*")
public class ArticleController extends HttpServlet {

	ArticleDB db = new ArticleDB();
	ReplyDB rdb = new ReplyDB();
	
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
		
		if(method.equals("POST")) {
			postProcess(req, resp);
			
		} else if(method.equals("GET")) {
			getProcess(req, resp);
		}
		
	}
	
	private void postProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String func = (String)request.getAttribute("func");
		
		if (func.equals("add")) {
			doAdd(request, response);
			
		} else if(func.equals("update")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			db.updateArticle(idx, title, body);
			response.sendRedirect("/article/detail?idx=" + idx);
			
		} else if(func.equals("delete")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			db.deleteArticle(idx);
			
			response.sendRedirect("/article/list");			
			
		} else if(func.equals("addReply")) {
			
			int articleIdx = Integer.parseInt(request.getParameter("articleIdx"));// 게시물 번호
			String body = request.getParameter("body"); // 내용
			String nickname = request.getParameter("nickname");// 닉네임
			
			rdb.insertReply(articleIdx, body, nickname);
			response.sendRedirect("/article/detail?idx=" + articleIdx);
			
		} else if(func.equals("doReplyUpdate")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			String body = request.getParameter("body");
			int parentIdx = Integer.parseInt(request.getParameter("parentIdx"));
			rdb.updateReply(idx, body);
			response.sendRedirect("/article/detail?idx=" + parentIdx);
		}
	}
	
	private void getProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String func = (String)request.getAttribute("func");
		
		if (func.equals("add")) {

			doAdd(request, response);
			
		} else if (func.equals("list")) {
			list(request, response);

		} else if(func.equals("showAddForm")) {
			
			// 로그인한 사람에게만 보여주는 코드
			
			RequestDispatcher rd = request.getRequestDispatcher("/addForm.jsp");
			rd.forward(request, response);
		} else if(func.equals("detail")) {
			
			// 로그인한 사람에게만 보여주는 코드
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			Article article = db.getArticleByIdx(idx);
			ArrayList<Reply> replies = rdb.getRepliesByArticleIdx(idx);
			
			request.setAttribute("article", article);
			request.setAttribute("replies", replies);
			
			forward(request, response, "/detail.jsp");
			
		} else if(func.equals("showUpdateForm")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			Article article = db.getArticleByIdx(idx);		
			request.setAttribute("article", article);			
			forward(request, response, "/updateForm.jsp");
			
		} else if(func.equals("showReplyForm")) {
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			Reply reply = rdb.getReplyByIdx(idx);
			request.setAttribute("reply", reply);
			
			forward(request, response, "/replyForm.jsp");
		} 
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String nickname = request.getParameter("nickname");

		// 주소록 추가
		db.insertArticle(title, body, nickname);
		
		
		// 포워드 => 요청 정보를 재사용. url 안바뀜
		// 리다이렉트 => 새로운 요청을 보냄. url 바꿈.
		list(request, response); // 추가 후 목록페이지 보여주기 -> 포워딩 방식은 아닌걸로..
		
		//response.sendRedirect("/article/list");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Article> articleList = db.getAllArticles();
		request.setAttribute("articleList", articleList);
		
		// 경로 -> url
		
		// 프로토콜://서버주소:포트번호/서버패스/자원명
		//   - http://localhost:9000/web-board/list.jsp
		// root -> 서버 path
		//   - http://localhost:9000/web-board => root
		
		// 현재 url -> http://localhost:9000/article/list
		// list.jsp -> 현재 경로 기준
		//   - http://localhost:9000/article/list/list.jsp
		// /list.jsp -> root 경로 기준
	//   - http://localhost:9000/list.jsp
		
		// 팝업을 띄울지 말지 결정해서 띄우겠다.
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("popupYn")) {
					request.setAttribute("popupYn", c.getValue());
				}
			}	
		}		
		
		forward(request, response, "/list.jsp");
		//response.sendRedirect("/article/list");
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
