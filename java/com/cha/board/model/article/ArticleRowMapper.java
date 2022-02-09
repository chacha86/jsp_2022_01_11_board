package com.cha.board.model.article;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
	
	public Article getRow(ResultSet rs) throws SQLException {
		
		int idx = rs.getInt("idx");
		String title = rs.getString("title");
		String body = rs.getString("body");
		String nickname = rs.getString("nickname");
		String regDate = rs.getString("regDate");

		Article article = new Article(idx, title, body, nickname, regDate);
		
		return article;
	}
}
