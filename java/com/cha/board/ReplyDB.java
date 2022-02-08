package com.cha.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReplyDB {
	String url = "jdbc:mysql://localhost:3306/b1?serverTimezone=UTC";
	String user = "root";
	String pass = "";
	String driver = "com.mysql.cj.jdbc.Driver";

	private Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("Connection 가져오는 중 문제 발생");
		}

		return conn;
	}
	
	
public ArrayList<Reply> getReplyList(String sql) {
		
		Connection conn = getConnection();

		ArrayList<Reply> replyList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx");
				int parentIdx = rs.getInt("parentIdx");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				String regDate = rs.getString("regDate");

				Reply reply = new Reply(idx, parentIdx, body, nickname, regDate);
				replyList.add(reply);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return replyList;
	}
	
	public void updateQuery(String sql) {
		Connection conn = getConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertReply(int articleIdx, String body, String nickname) {
		String sql = String.format(
				"INSERT INTO articleReply SET parentIdx = %d, `body` = '%s', nickname = '%s', regDate = NOW()", articleIdx, body,
				nickname);
		updateQuery(sql);
		
	}

	public ArrayList<Reply> getRepliesByArticleIdx(int parentIdx) {
		String sql = String.format("SELECT * FROM articleReply WHERE parentIdx = %d", parentIdx);
		
		return getReplyList(sql);
	}

	public Reply getReplyByIdx(int idx) {
		Reply reply = null;
		
		String sql = String.format("SELECT * FROM articleReply WHERE idx = %d", idx);		
		ArrayList<Reply> replies = getReplyList(sql);
		
		if(replies.size() > 0) {
			reply = replies.get(0); 
		}
		
		return reply;
		
	}

	public void updateReply(int idx, String body) {
		System.out.println(idx);
		System.out.println(body);
		String sql = String.format("UPDATE articleReply  SET `body` = '%s' WHERE idx = %d", body, idx);		
		updateQuery(sql);		
		
	}
	
}
