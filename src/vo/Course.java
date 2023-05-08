package vo;

import java.util.Date;

public class Course {

	private int no;
	private String name;
	private int quota;
	private int reqCnt;
	private String status;
	private Date createDate;
	private String teacherId;
	
	public Course() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}


	public int getReqCnt() {
		return reqCnt;
	}


	public void setReqCnt(int reqCnt) {
		this.reqCnt = reqCnt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Course [no=" + no + ", name=" + name + ", quota=" + quota + ", reqCnt=" + reqCnt + ", status=" + status
				+ ", createDate=" + createDate + ", teacherId=" + teacherId + "]";
	}
	
}
