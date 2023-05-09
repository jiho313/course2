package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DaoHelper;
import vo.Registration;

public class RegistrationDao {

	private static RegistrationDao instance = new RegistrationDao();
	private RegistrationDao() {}
	public static RegistrationDao getInstance() {
		return instance;
	}
	
	public Registration getRegistration(String studentId, int courseNo) {
		return DaoHelper.selectOne("registrationdao.getRegistration", 
					rs -> {
						Registration reg = new Registration();
						reg.setNo(rs.getInt("reg_no"));
						reg.setStudentId(rs.getString("student_id"));
						reg.setCourseNo(rs.getInt("course_no"));
						reg.setCanceled(rs.getString("reg_canceled"));
						reg.setCreateDate(rs.getDate("reg_create_date"));
						
						return reg;
					}, studentId, courseNo);
	}
	
	public void insertRegistration(String studentId, int courseNo) {
		DaoHelper.update("registrationdao.insertRegistration", 
				studentId, courseNo);
	}
	
	public List<Map<String, Object>> getRegistrations(String studentId) {
		return DaoHelper.selectList("registrationdao.getRegistrations",
				rs -> {
					Map<String, Object> map = new HashMap<>();
					map.put("no", rs.getInt("reg_no"));
					map.put("createDate", rs.getDate("reg_create_date"));
					map.put("canceled", rs.getString("reg_canceled"));
					map.put("name", rs.getString("course_name"));
					
					return map;
				}, studentId);
	}
	
	public Registration getRegistration(int regNo) {
		return DaoHelper.selectOne("registrationdao.getRegistrationByNo", 
				rs -> {
					Registration registration = new Registration();
					registration.setNo(rs.getInt("reg_no"));
					registration.setStudentId(rs.getString("student_id"));
					registration.setCourseNo(rs.getInt("course_no"));
					registration.setCanceled(rs.getString("reg_canceled"));
					registration.setCreateDate(rs.getDate("reg_create_date"));
					return registration;
				}, regNo);
	}
	
	public void updateRegistration(Registration registration) {
		DaoHelper.update("registrationdao.updateRegistration", 
				registration.getCanceled(),
				registration.getNo());
	}
}
















