package com.cha.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDB {
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

	public void insertMember(String loginId, String loginPw, String nickname) {
		String sql = String.format(
				"INSERT INTO `member` SET loginId = 'hong123', loginPw = 'h1234', nickname = '홍길동', regDate = NOW()", loginId, loginPw,
				nickname);
		updateQuery(sql);
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
	
}
