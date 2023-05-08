package service;

import dao.StudentDao;
import vo.Student;

public class StudentService {

	private static StudentService instance = new StudentService();
	private StudentService() {}
	public static StudentService getInstance() {
		return instance;
	}
	
	private StudentDao studentDao = StudentDao.getInstance();
	
	public void 회원가입(Student student) {
		// 아이디 중복체크하기
		Student savedStudent = studentDao.getStudentById(student.getId());
		if (savedStudent != null) {
			throw new RuntimeException("이미 사용중인 아이디입니다.");
		}
		
		// 이메일 중복체크하기
		savedStudent = studentDao.getStudentByEmail(student.getEmail());
		if (savedStudent != null) {
			throw new RuntimeException("이미 사용중인 이메일입니다.");
		}
		
		// 학생정보 저장하기
		studentDao.insertStudent(student);
	}
	
	public Student 로그인(String id, String password) {
		// 수강생 정보가 존재하는지 체크하기
		Student savedStudent = studentDao.getStudentById(id);
		if (savedStudent == null) {
			throw new RuntimeException("존재하지 않는 아이디입니다.");
		}

		// 탈퇴한 수강생인지 체크하기
		if ("Y".equals(savedStudent.getDeleted())) {
			throw new RuntimeException("삭제된 아이디입니다.");			
		}
		
		// 비밀번호 체크하기
		if (!savedStudent.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");						
		}
		
		return savedStudent;		
	}
}

















