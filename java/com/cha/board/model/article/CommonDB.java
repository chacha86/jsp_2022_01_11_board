package com.cha.board.model.article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommonDB {

	// DB 접속 정보
	String url = "jdbc:mysql://localhost:3306/b1?serverTimezone=UTC";
	String user = "root";
	String pass = "";
	String driver = "com.mysql.cj.jdbc.Driver";

	// 연결
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

	// 여러개 가져와
	public<T> ArrayList<T> selectList(String sql, RowMapper<T> mapper) {

		Connection conn = getConnection();
		ArrayList<T> resultList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				T row = mapper.getRow(rs);
				resultList.add(row);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	// 한개 가져와
	public <T> T getOne(String sql, RowMapper<T> mapper) {
		
		T result = null;
		ArrayList<T> resultList = selectList(sql, mapper);
		
		if(resultList.size() > 0) {
			result = resultList.get(0);
		}
		
		return result;
	}
	
	// 반영해
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
