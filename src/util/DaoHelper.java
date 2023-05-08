package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoHelper {
	
	public static <T> T selectOne(String sql, RowMapper<T> rowMapper, Object...params) {
		try {
			T t = null;
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rowMapper.mapRow(rs);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			return t;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static <T> List<T> selectList(String sql, RowMapper<T> rowMapper, Object...params) {
		try {
			List<T> list = new ArrayList<>();
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rowMapper.mapRow(rs));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return list;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void update(String sql, Object... params) {
	
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private static void setParams(PreparedStatement pstmt, Object[] params) throws SQLException {
		if (params.length > 0) {
			int index = 1;
			for (Object param : params) {
				if (param instanceof String) {
					pstmt.setString(index, (String) param);
				} else if (param instanceof Date) {
					Date date = (Date) param;
					pstmt.setDate(index, new java.sql.Date(date.getTime()));
				} else if (param instanceof Integer) {
					pstmt.setInt(index, (Integer) param);
				} else if (param instanceof Long) {
					pstmt.setLong(index, (Long) param);
				} else if (param instanceof Double) {
					pstmt.setDouble(index, (Double) param);
				}
				index++;
			}
		}
	}
	
	public static interface RowMapper<T> {
		T mapRow(ResultSet rs) throws SQLException;
	}
}
