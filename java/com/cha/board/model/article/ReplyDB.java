package com.cha.board.model.article;

import java.util.ArrayList;

import com.cha.board.model.common.CommonDB;

public class ReplyDB {
	
	CommonDB db = new CommonDB();
	
	public void insertReply(int articleIdx, String body, int memberIdx) {
		String sql = String.format(
				"INSERT INTO articleReply SET parentIdx = %d, `body` = '%s', memberIdx = '%d', regDate = NOW()", articleIdx, body,
				memberIdx);
		db.updateQuery(sql);
		
	}

	public ArrayList<Reply> getRepliesByArticleIdx(int parentIdx) {
		String sql = String.format("SELECT ar.*, m.nickname FROM articleReply ar INNER JOIN `member` m ON ar.memberIdx = m.idx WHERE parentIdx = %d", parentIdx);
		return db.selectList(sql, new ReplyRowMapper());
	}

	public Reply getReplyByIdx(int idx) {
			
		String sql = String.format("SELECT ar.*, m.nickname FROM articleReply ar INNER JOIN `member` m ON ar.memberIdx = m.idx WHERE ar.idx = %d", idx);		
		return db.getOne(sql, new ReplyRowMapper());
		
	}

	public void updateReply(int idx, String body) {
		String sql = String.format("UPDATE articleReply  SET `body` = '%s' WHERE idx = %d", body, idx);		
		db.updateQuery(sql);		
		
	}

	public void deleteReply(int idx) {
		String sql = String.format("DELETE FROM articleReply WHERE idx = %d", idx);
		db.updateQuery(sql);
	}
	
}
