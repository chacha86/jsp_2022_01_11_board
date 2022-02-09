package com.cha.board.model.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				"INSERT INTO `member` SET loginId = '%s', loginPw = '%s', nickname = '%s', regDate = NOW()", loginId,
				loginPw, nickname);
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

	// 다건, 단건
	
	public ArrayList<Member> getMemberList(String sql) {

		Connection conn = getConnection();

		ArrayList<Member> memberList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String loginId = rs.getString("loginId");
				String loginPw = rs.getString("loginPw");
				String nickname = rs.getString("nickname");
				String regDate = rs.getString("regDate");

				Member member = new Member(idx, loginId, loginPw, nickname, regDate);
				memberList.add(member);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return memberList;
	}

	public Member getMemberByIdx(int idx) {
		Member member = null;
		
		String sql = String.format("SELECT * FROM `member` WHERE idx = %d", idx);		
		ArrayList<Member> members = getMemberList(sql);
		
		if(members.size() > 0) {
			member = members.get(0); 
		}
		
		return member;
		
	}
	
	public int getMemberIdxByLoginInfo(String loginId, String loginPw) {
		String sql = String.format("SELECT idx FROM `member` WHERE loginId = '%s' AND loginPw = '%s'", loginId, loginPw);
			
		Connection conn = getConnection();
		int memberIdx = 0;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				memberIdx = rs.getInt("idx");				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return memberIdx;
		
	}

}
