package controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import service.CourseService;
import service.StudentService;
import service.TeacherService;
import util.KeyboardReader;
import vo.Course;
import vo.Student;
import vo.Teacher;

public class CourseController {

	private KeyboardReader keyboard = new KeyboardReader();
	
	private StudentService studentService = StudentService.getInstance();
	private TeacherService teacherService = TeacherService.getInstance();
	private CourseService courseService = CourseService.getInstance();
	
	
	private LoginUser loginUser;
	
	public void menu() {
		
		try {
			if (loginUser == null) {
				System.out.println("-------------------------------------------------------------");
				System.out.println("1.로그인(학생)  2.로그인(강사)  3.가입(학생)  4.가입(강사)  0.종료");
				System.out.println("-------------------------------------------------------------");
			} else {
				if ("학생".equals(loginUser.getType())) {
					System.out.println("-------------------------------------------------------------");				
					System.out.println("1.과정조회  2.과정신청  3.등록취소  4.신청현황  0.종료");
					System.out.println("-------------------------------------------------------------");				
				} else if ("강사".equals(loginUser.getType())) {
					System.out.println("-------------------------------------------------------------");				
					System.out.println("1.과정조회  2.과정등록  3.과정취소  4.과정현황  0.종료");					
					System.out.println("-------------------------------------------------------------");				
				}
			}
			System.out.println();
			System.out.print("### 메뉴번호: ");
			int menu = keyboard.readInt();
			System.out.println();
			
			if (menu == 0) {
				System.out.println("<< 프로그램 종료 >>");
				System.out.println("### 프로그램을 종료합니다.");
				System.exit(0);
			}
			
			if (loginUser == null) {
				if (menu == 1) {
					학생로그인();
				} else if (menu == 2) {
					강사로그인();
				} else if (menu == 3) {
					학생회원가입();
				} else if (menu == 4) {
					강사회원가입();
				}
			} else {
				if ("학생".equals(loginUser.getType())) {
					if (menu == 1) {
						학생과정조회();
					} else if (menu == 2) {
						학생과정신청();
					} else if (menu == 3) {
						학생등록취소();
					} else if (menu == 4) {
						학생신청현황조회();
					}
				
				} else if ("강사".equals(loginUser.getType())) {
					if (menu == 1) {
						강사과정조회();
					} else if (menu == 2) {
						강사과정등록();
					} else if (menu == 3) {
						강사과정취소();
					} else if (menu == 4) {
						강사과정현황조회();
					}
				}
			}
			
			
			
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace(System.out);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		menu();
	}
	
	private void 학생로그인() {
		System.out.println("<< 학생 로그인 >>");
		
		// 입력값 읽어오기
		System.out.print("### 아이디 입력: ");
		String id = keyboard.readString();
		System.out.print("### 비밀번호 입력: ");
		String password = keyboard.readString();
		
		// 업무로직 호출하기
		Student student = studentService.로그인(id, password);
		
		// 인증된 학생정보를 서버에서 보관하고 있기
		loginUser = new LoginUser(student.getId(), student.getName(), "학생");
		
		System.out.println("### 학생 로그인이 완료되었습니다.");
	}
	
	private void 강사로그인() {
		System.out.println("<< 강사 로그인 >>");
		
		// 입력값 읽어오기
		System.out.print("### 아이디 입력: ");
		String id = keyboard.readString();
		System.out.print("### 비밀번호 입력: ");
		String password = keyboard.readString();
		
		Teacher teacher = teacherService.로그인(id, password);
		
		loginUser = new LoginUser(teacher.getId(), teacher.getName(), "강사");
		
		System.out.println("### 강사 로그인이 완료되었습니다.");
	}
	
	private void 학생회원가입() {
		// 메뉴 출력하기
		System.out.println("<< 학생 회원 가입 >>");
		
		// 입력값 읽어오기
		System.out.print("### 아이디 입력: ");
		String id = keyboard.readString();
		System.out.print("### 비밀번호 입력: ");
		String password = keyboard.readString();
		System.out.print("### 이름 입력: ");
		String name = keyboard.readString();
		System.out.print("### 이메일 입력: ");
		String email = keyboard.readString();
		System.out.print("### 전화번호 입력: ");
		String phone = keyboard.readString();
		
		// 입력값을 Student객체에 저장하기
		Student student = new Student();
		student.setId(id);
		student.setPassword(password);
		student.setName(name);
		student.setEmail(email);
		student.setPhone(phone);
		
		// 업무로직 호출하기
		studentService.회원가입(student);
		
		// 완료 메세지 출력하기
		System.out.println("### 학생 회원가입이 완료되었습니다.");		
	}
	
	private void 강사회원가입() {
		// 메뉴 출력하기
		System.out.println("<< 강사 회원 가입 >>");
		
		// 입력값 읽어오기
		System.out.print("### 아이디 입력: ");
		String id = keyboard.readString();
		System.out.print("### 비밀번호 입력: ");
		String password = keyboard.readString();
		System.out.print("### 이름 입력: ");
		String name = keyboard.readString();
		System.out.print("### 이메일 입력: ");
		String email = keyboard.readString();
		System.out.print("### 전화번호 입력: ");
		String phone = keyboard.readString();
		System.out.print("### 급여 입력: ");
		int salary = keyboard.readInt();
		
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setPassword(password);
		teacher.setName(name);
		teacher.setEmail(email);
		teacher.setPhone(phone);
		teacher.setSalary(salary);
		
		teacherService.회원가입(teacher);
		
		System.out.println("### 강사 회원가입이 완료되었습니다.");
		
	}
	
	private void 학생과정조회() {
		System.out.println("<< 개설 과정 조회 >>");
		
		List<Map<String, Object>> courses = courseService.개설과정조회();
		if (courses.isEmpty()) {
			System.out.println("### 등록가능한 과정이 존재하지 않습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("과정번호\t모집정원\t신청자수\t담당강사\t과정명");
			System.out.println("------------------------------------------------------------------------");
			for (Map<String, Object> map : courses) {
				int no = (Integer) map.get("no");
				int quota = (Integer) map.get("quota");
				int reqCnt = (Integer) map.get("reqCnt");
				String teacherName = (String) map.get("teacherName");
				String name = (String) map.get("name");
				
				System.out.print(no + "\t");
				System.out.print(quota + "\t");
				System.out.print(reqCnt + "\t");
				System.out.print(teacherName + "\t");
				System.out.println(name);
			}
			System.out.println("------------------------------------------------------------------------");
		}
	}
	
	private void 학생과정신청() {
		System.out.println("<< 수강 신청 >>");
		
		System.out.println("### 과정번호 입력: ");
		int courseNo = keyboard.readInt();
		
		courseService.수강신청(courseNo, loginUser.getId());
		
		System.out.println("### 수강신청이 완료되었습니다.");
	}
	
	private void 학생등록취소() {
		System.out.println("<< 수강 취소 >>");
		
		System.out.print("### 등록번호 입력: ");
		int regNo = keyboard.readInt();
		
		courseService.수강취소(regNo, loginUser.getId());
		
		System.out.println("### 수강취소가 완료되었습니다.");
	}
	
	private void 학생신청현황조회() {
		System.out.println("<< 수강 신청 현황 >>");
		
		List<Map<String, Object>> registrations 
			= courseService.수강신청현황(loginUser.getId());
		
		if (registrations.isEmpty()) {
			System.out.println("### 수강신청 내역이 존재하지 않습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("등록번호\t등록일자\t\t취소여부\t과정명");
			System.out.println("------------------------------------------------------------------------");
			for (Map<String, Object> map : registrations) {
				int no = (Integer) map.get("no");
				Date createDate = (Date) map.get("createDate");
				String canceled = (String) map.get("canceled");
				String name = (String) map.get("name");
				
				System.out.print(no + "\t");
				System.out.print(createDate + "\t");
				System.out.print(canceled + "\t");
				System.out.println(name);
			}
			System.out.println("------------------------------------------------------------------------");
		}
	}
	
	private void 강사과정조회() {
		
	}
	
	private void 강사과정등록() {
		System.out.println("<< 새 과정 등록>>");
		
		System.out.print("### 과정명 입력: ");
		String name = keyboard.readString();
		System.out.print("### 모집정원 입력: ");
		int quota = keyboard.readInt();
		
		Course course = new Course();
		course.setName(name);
		course.setQuota(quota);
		course.setTeacherId(loginUser.getId());
		
		courseService.새과정등록(course);
		
		System.out.println("### 새 과정 등록이 완료되었습니다.");
	}
	
	private void 강사과정취소() {
		
	}
	
	private void 강사과정현황조회() {
		
	}
	
	public static void main(String[] args) {
		new CourseController().menu();
	}
}
