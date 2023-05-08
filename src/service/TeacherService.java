package service;

import dao.TeacherDao;
import vo.Teacher;

public class TeacherService {
	
	private static TeacherService instance = new TeacherService();
	private TeacherService() {}
	public static TeacherService getInstance() {
		return instance;
	}

	private TeacherDao teacherDao = TeacherDao.getInstance();
	
	public void 회원가입(Teacher teacher) {
		Teacher savedTeacher = teacherDao.getTeacherById(teacher.getId());
		if (savedTeacher != null) {
			throw new RuntimeException("이미 사용중인 아이디입니다.");
		}
		
		savedTeacher = teacherDao.getTeacherByEmail(teacher.getEmail());
		if (savedTeacher != null) {
			throw new RuntimeException("이미 사용중인 이메일입니다.");
		}
		
		teacherDao.insertTeacher(teacher);
	}
	
	public Teacher 로그인(String id, String password) {
		Teacher savedTeacher = teacherDao.getTeacherById(id);
		if (savedTeacher == null) {
			throw new RuntimeException("존재하지 않는 아이디입니다.");
		}
		
		if ("Y".equals(savedTeacher.getRetired())) {
			throw new RuntimeException("삭제된 아이디입니다.");
		}
		
		if (!savedTeacher.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return savedTeacher;
	}
}
