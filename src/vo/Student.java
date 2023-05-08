package vo;

public class Student extends Person {

	private String deleted;
	
	public Student() {}


	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		String person = super.toString();
		return "Student [" + person + " deleted=" + deleted + "]";
	}
	
}
