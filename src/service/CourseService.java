package service;

import java.util.List;
import java.util.Map;

import dao.CourseDao;
import dao.RegistrationDao;
import vo.Course;
import vo.Registration;

public class CourseService {

	private static CourseService instance = new CourseService();
	private CourseService() {}
	public static CourseService getInstance() {
		return instance;
	}
	
	private CourseDao courseDao = CourseDao.getInstance();
	private RegistrationDao registrationDao = RegistrationDao.getInstance();	
	
	public void 새과정등록(Course course) {
		courseDao.insertCourse(course);
	}
	
	public List<Map<String, Object>> 개설과정조회() {
		return courseDao.getCourses("모집중");
	}
	
	public void 수강신청(int courseNo, String studentId) {
		// 과정정보 존재여부 체크하기
		Course course = courseDao.getCourse(courseNo);
		if (course == null) {
			throw new RuntimeException("과정정보가 존재하지 않습니다.");
		}
		
		// 과정상태 체크하기
		if (!"모집중".equals(course.getStatus())) {
			throw new RuntimeException("현재 모집중인 과정이 아닙니다.");
		}
		
		// 중복신청여부 체크하기
		Registration registration = registrationDao.getRegistration(studentId, courseNo);
		if (registration != null) {
			throw new RuntimeException("이미 수강신청(취소)된 과정입니다.");			
		}
		
		// 수강신청정보 저장
		registrationDao.insertRegistration(studentId, courseNo);
		
		// 개설과정정보 업데이트(신청자수, 과정상태)
		course.setReqCnt(course.getReqCnt() + 1);
		if (course.getQuota() == course.getReqCnt()) {
			course.setStatus("모집완료");
		}
		courseDao.updateCourse(course);	
	}
	
	public List<Map<String, Object>> 수강신청현황(String studentId) {
		return registrationDao.getRegistrations(studentId);
	}
	
	public void 수강취소(int regNo, String studentId) {
		// 등록정보 조회, 존재여부 체크
		Registration registration = registrationDao.getRegistration(regNo);
		if (registration == null) {
			throw new RuntimeException("수강신청 정보가 존재하지 않습니다.");
		}

		// 이미 수강취소된 과정인지 체크
		if ("Y".equals(registration.getCanceled())) {
			throw new RuntimeException("이미 수강취소처리된 과정입니다.");
		}
		
		// 등록정보가 로그인한 학생의 등록정보인지 체크
		if (!registration.getStudentId().equals(studentId)) {
			throw new RuntimeException("본인이 신청한 과정만 수강취소할 수 있습니다.");
		}
		
		// 등록정보의 취소여부를 'Y'로 변경
		registration.setCanceled("Y");
		registrationDao.updateRegistration(registration);
		
		// 과정정보 수정(신청자수, 과정상태)
		Course course = courseDao.getCourse(registration.getCourseNo());
		course.setReqCnt(course.getReqCnt() - 1);
		if ("모집완료".equals(course.getStatus())) {
			course.setStatus("모집중");
		}
		courseDao.updateCourse(course);
		
	}
}


















