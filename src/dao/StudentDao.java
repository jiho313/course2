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
		String sql = "select * "
				+ " from academy_students "
				+ " where student_id = ? ";
		return DaoHelper.selectOne(sql, rs -> {
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
		String sql = "select * "
				+ " from academy_students "
				+ " where student_email = ? ";
		
		return DaoHelper.selectOne(sql, rs -> {
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
		String sql = "insert into academy_students "
				+ "(student_id, student_password, student_name, student_email, student_phone)"
				+ "values "
				+ "(?,?,?,?,?)";
		
		DaoHelper.update(sql,
				student.getId(),
				student.getPassword(),
				student.getName(),
				student.getEmail(),
				student.getPhone());
	}
}
