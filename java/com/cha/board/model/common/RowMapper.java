package com.cha.board.model.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	public T getRow(ResultSet rs) throws SQLException;
}
