package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ConnUtils;
import vo.Registration;

public class RegistrationDao {

	private static RegistrationDao instance = new RegistrationDao();
	private RegistrationDao() {}
	public static RegistrationDao getInstance() {
		return instance;
	}
	
	public Registration getRegistration(String studentId, int courseNo) {
		String sql = "select * "
				+ " from academy_course_registrations "
				+ " where student_id = ? "
				+ " and course_no = ? ";
		
		try {
			Registration reg = null;
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			pstmt.setInt(2, courseNo);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				reg = new Registration();
				reg.setNo(rs.getInt("reg_no"));
				reg.setStudentId(rs.getString("student_id"));
				reg.setCourseNo(rs.getInt("course_no"));
				reg.setCanceled(rs.getString("reg_canceled"));
				reg.setCreateDate(rs.getDate("reg_create_date"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return reg;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void insertRegistration(String studentId, int courseNo) {
		String sql = "insert into academy_course_registrations "
				+ "(reg_no, student_id, course_no) "
				+ "values "
				+ "(reg_seq.nextval, ?, ?)";
		
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			pstmt.setInt(2, courseNo);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<Map<String, Object>> getRegistrations(String studentId) {
		String sql = "select A.reg_no, "
				+ "          A.reg_create_date, "
				+ "			 A.reg_canceled, "
				+ "			 B.course_name "
				+ "from academy_course_registrations A, academy_courses B "
				+ "where A.course_no = B.course_no "
				+ "and A.student_id = ? "
				+ "order by a.reg_no asc ";
		
		try {
			List<Map<String, Object>> registrations = new ArrayList<>();
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("no", rs.getInt("reg_no"));
				map.put("createDate", rs.getDate("reg_create_date"));
				map.put("canceled", rs.getString("reg_canceled"));
				map.put("name", rs.getString("course_name"));
				
				registrations.add(map);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return registrations;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public Registration getRegistration(int regNo) {
		String sql = "select * "
				+ "from academy_course_registrations "
				+ "where reg_no = ?";
		
		try {
			Registration registration = null;
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regNo);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				registration = new Registration();
				registration.setNo(rs.getInt("reg_no"));
				registration.setStudentId(rs.getString("student_id"));
				registration.setCourseNo(rs.getInt("course_no"));
				registration.setCanceled(rs.getString("reg_canceled"));
				registration.setCreateDate(rs.getDate("reg_create_date"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return registration;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void updateRegistration(Registration registration) {
		String sql = "update academy_course_registrations "
				+ " set "
				+ "		reg_canceled = ? "
				+ " where reg_no = ? ";
		
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registration.getCanceled());
			pstmt.setInt(2, registration.getNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
















