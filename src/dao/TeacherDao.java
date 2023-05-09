package dao;

import util.DaoHelper;
import vo.Teacher;

public class TeacherDao {
	
	private static TeacherDao instance = new TeacherDao();
	private TeacherDao() {}
	public static TeacherDao getInstance() {
		return instance;
	}

	public Teacher getTeacherById(String teacherId) {
		return DaoHelper.selectOne("teacherdao.getTeacherById", 
				rs -> {
					Teacher teacher = new Teacher();
					teacher.setId(rs.getString("teacher_id"));
					teacher.setPassword(rs.getString("teacher_password"));
					teacher.setName(rs.getString("teacher_name"));
					teacher.setEmail(rs.getString("teacher_email"));
					teacher.setPhone(rs.getString("teacher_phone"));
					teacher.setSalary(rs.getInt("teacher_salary"));
					teacher.setRetired(rs.getString("teacher_retired"));
					teacher.setCreateDate(rs.getDate("teacher_create_date"));
					
					return teacher;
				}, teacherId);
	}
	
	public Teacher getTeacherByEmail(String teacherEmail) {
		return DaoHelper.selectOne("teacherdao.getTeacherByEmail", 
				rs -> {
					Teacher teacher = new Teacher();
					teacher.setId(rs.getString("teacher_id"));
					teacher.setPassword(rs.getString("teacher_password"));
					teacher.setName(rs.getString("teacher_name"));
					teacher.setEmail(rs.getString("teacher_email"));
					teacher.setPhone(rs.getString("teacher_phone"));
					teacher.setSalary(rs.getInt("teacher_salary"));
					teacher.setRetired(rs.getString("teacher_retired"));
					teacher.setCreateDate(rs.getDate("teacher_create_date"));
					
					return teacher;
				}, teacherEmail);
	}
	
	public void insertTeacher(Teacher teacher) {
		DaoHelper.update("teacherdao.insertTeacher", 
				teacher.getId(),
				teacher.getPassword(),
				teacher.getName(),
				teacher.getEmail(),
				teacher.getPhone(),
				teacher.getSalary());
	}
}
