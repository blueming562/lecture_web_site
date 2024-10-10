package wish_list;

public class ClassInfo {
	
	private int courseId;
	private int classId;
	private String courseName;
	private int departemntId;
	private String classification;
	private String semester;
	private int credit;
	private int professorId;
	private String roomNo;
	private int capacity;
	private int enrolled;
	private String dayOfWeek;
	private String startTime;
	private String endTime;
	private String departmentName;
	private String professorName;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getDepartemntId() {
		return departemntId;
	}
	public void setDepartemntId(int departemntId) {
		this.departemntId = departemntId;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
	public ClassInfo(int courseId, int classId, String courseName, int departemntId, String classification,
			String semester, int credit, int professorId, String roomNo, int capacity, int enrolled, String dayOfWeek,
			String startTime, String endTime, String departmentName, String professorName) {
		super();
		this.courseId = courseId;
		this.classId = classId;
		this.courseName = courseName;
		this.departemntId = departemntId;
		this.classification = classification;
		this.semester = semester;
		this.credit = credit;
		this.professorId = professorId;
		this.roomNo = roomNo;
		this.capacity = capacity;
		this.enrolled = enrolled;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.departmentName = departmentName;
		this.professorName = professorName;
	}
	
	public ClassInfo() {
		super();
	}
	
	@Override
	public String toString() {
		return "ClassInfo [courseId=" + courseId + ", classId=" + classId + ", courseName=" + courseName
				+ ", departemntId=" + departemntId + ", classification=" + classification + ", semester=" + semester
				+ ", credit=" + credit + ", professorId=" + professorId + ", roomNo=" + roomNo + ", capacity="
				+ capacity + ", enrolled=" + enrolled + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", departmentName=" + departmentName + ", professorName=" + professorName
				+ "]";
	}

}
