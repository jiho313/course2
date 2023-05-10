package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DaoHelper;
import vo.Course;

public class CourseDao {

	private static CourseDao courseDao = new CourseDao();
	private CourseDao() {}
	public static CourseDao getInstance() {
		return courseDao;
	}
	
	public void insertCourse(Course course) {
		DaoHelper.update("coursedao.insertCourse", 
				course.getName(), 
				course.getQuota(), 
				course.getTeacherId());		
	}
	
	public List<Map<String, Object>> getCourses(String status) {
		return DaoHelper.selectList("coursedao.getCourses", rs -> {
			Map<String, Object> map = new HashMap<>();
			
			map.put("no", rs.getInt("course_no"));
			map.put("name", rs.getString("course_name"));
			map.put("quota", rs.getInt("course_quota"));
			map.put("reqCnt", rs.getInt("course_req_cnt"));
			map.put("teacherId", rs.getString("teacher_id"));
			map.put("teacherName", rs.getString("teacher_name"));
			
			return map;
		}, status);
	}
	
	
	public Course getCourse(int courseNo) {
		return DaoHelper.selectOne("coursedao.getCourse", rs -> {
			Course course = new Course();
			course.setNo(rs.getInt("course_no"));
			course.setName(rs.getString("course_name"));
			course.setQuota(rs.getInt("course_quota"));
			course.setReqCnt(rs.getInt("course_req_cnt"));
			course.setStatus(rs.getString("course_status"));
			course.setCreateDate(rs.getDate("course_create_date"));
			course.setTeacherId(rs.getString("teacher_id"));
			return course;
		}, courseNo);
	}
	
	public void updateCourse(Course course) {
		DaoHelper.update("coursedao.updateCourse", 
				course.getName(),
				course.getQuota(),
				course.getReqCnt(),
				course.getStatus(),
				course.getNo());
	}
}





