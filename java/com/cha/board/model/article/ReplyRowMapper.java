package com.cha.board.model.article;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cha.board.model.common.RowMapper;

public class ReplyRowMapper implements RowMapper<Reply> {

	@Override
	public Reply getRow(ResultSet rs) throws SQLException {
		
		int idx = rs.getInt("idx");
		int parentIdx = rs.getInt("parentIdx");
		String body = rs.getString("body");
		int memberIdx = rs.getInt("memberIdx");
		String regDate = rs.getString("regDate");
		String nickname = rs.getString("nickname");

		Reply reply = new Reply(idx, parentIdx, body, memberIdx, nickname, regDate);
		return reply;
	}

}
