package dao;

import util.DaoHelper;
import vo.Student;

public class StudentDao {
	
	// 싱글턴(Singleton) 객체
	private static StudentDao instance = new StudentDao();
	private StudentDao() {}
	public static StudentDao getInstance() {
		return instance;
	}	

	public Student getStudentById(String studentId) {		
		return DaoHelper.selectOne("studentdao.getStudentById", rs -> {
			Student student = new Student();
			student.setId(rs.getString("student_id"));
			student.setPassword(rs.getString("student_password"));
			student.setName(rs.getString("student_name"));
			student.setEmail(rs.getString("student_email"));
			student.setPhone(rs.getString("student_phone"));
			student.setCreateDate(rs.getDate("student_create_date"));
			return student;
		}, studentId);
	}
	
	public Student getStudentByEmail(String studentEmail) {
		return DaoHelper.selectOne("studentdao.getStudentByEmail", rs -> {
			Student student = new Student();
			student.setId(rs.getString("student_id"));
			student.setPassword(rs.getString("student_password"));
			student.setName(rs.getString("student_name"));
			student.setEmail(rs.getString("student_email"));
			student.setPhone(rs.getString("student_phone"));
			student.setCreateDate(rs.getDate("student_create_date"));
			return student;
		}, studentEmail);
	}
	
	public void insertStudent(Student student) {
		DaoHelper.update("studentdao.insertStudent",
				student.getId(),
				student.getPassword(),
				student.getName(),
				student.getEmail(),
				student.getPhone());
	}
}
