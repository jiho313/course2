package vo;

import java.util.Date;

public class Registration {

	private int no;
	private String studentId;
	private int courseNo;
	private String canceled;
	private Date createDate;
	
	public Registration() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Registration [no=" + no + ", studentId=" + studentId + ", courseNo=" + courseNo + ", canceled="
				+ canceled + ", createDate=" + createDate + "]";
	}
	
}
