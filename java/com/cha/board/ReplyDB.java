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
				int memberIdx = rs.getInt("memberIdx");
				String regDate = rs.getString("regDate");
				String nickname = rs.getString("nickname");

				Reply reply = new Reply(idx, parentIdx, body, memberIdx, nickname, regDate);
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
	
	public void insertReply(int articleIdx, String body, int memberIdx) {
		String sql = String.format(
				"INSERT INTO articleReply SET parentIdx = %d, `body` = '%s', memberIdx = '%d', regDate = NOW()", articleIdx, body,
				memberIdx);
		updateQuery(sql);
		
	}

	public ArrayList<Reply> getRepliesByArticleIdx(int parentIdx) {
		String sql = String.format("SELECT ar.*, m.nickname FROM articleReply ar INNER JOIN `member` m ON ar.memberIdx = m.idx WHERE parentIdx = %d", parentIdx);
		
		return getReplyList(sql);
	}

	public Reply getReplyByIdx(int idx) {
		Reply reply = null;
		
		String sql = String.format("SELECT ar.*, m.nickname FROM articleReply ar INNER JOIN `member` m ON ar.memberIdx = m.idx WHERE ar.idx = %d", idx);		
		ArrayList<Reply> replies = getReplyList(sql);
		
		if(replies.size() > 0) {
			reply = replies.get(0); 
		}
		
		return reply;
		
	}

	public void updateReply(int idx, String body) {
		String sql = String.format("UPDATE articleReply  SET `body` = '%s' WHERE idx = %d", body, idx);		
		updateQuery(sql);		
		
	}


	public void deleteReply(int idx) {
		String sql = String.format("DELETE FROM articleReply WHERE idx = %d", idx);
		updateQuery(sql);
	}
	
}
