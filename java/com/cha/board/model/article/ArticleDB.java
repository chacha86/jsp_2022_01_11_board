package com.cha.board.model.article;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cha.board.model.common.CommonDB;

public class ArticleDB {
	
	CommonDB db = new CommonDB();
	
	public ArrayList<Article> getAllArticles() {
		
		String sql = "SELECT * FROM article";
		ArrayList<Article> articles = db.selectList(sql, new ArticleRowMapper());
		return articles;
		
	}
	
	public Article getArticleByIdx(int idx) {
		
		String sql = String.format("SELECT * FROM article WHERE idx = %d", idx);		
		Article article = db.getOne(sql, new ArticleRowMapper());
		
		return article;
	}
	
	public void insertArticle(String title, String body, String nickname) {
		String sql = String.format(
				"INSERT INTO article SET `title` = '%s', body = '%s', nickname = '%s', regDate = NOW()", title, body,
				nickname);
		db.updateQuery(sql);
	}		

	public void updateArticle(int idx, String title, String body) {
		String sql = String.format("UPDATE article SET title = '%s', `body` = '%s' WHERE idx = %d", title, body, idx);
		db.updateQuery(sql);
	}

	public void deleteArticle(int idx) {
		String sql = String.format("DELETE FROM article WHERE idx = %d", idx);
		db.updateQuery(sql);
	}

	public ArrayList<Article> getArticlesForPage(Pagination pagination) {
		String sql = String.format("SELECT * FROM article LIMIT %d, %d", pagination.getStartIndex(), pagination.getItemCntPerPage()); 
		return db.selectList(sql, new ArticleRowMapper());
	}

	
}
