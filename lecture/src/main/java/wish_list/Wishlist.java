package wish_list;

public class Wishlist {
	
	private int wishlistId;
	private int studentId;
	private int courseId;
	private int classId;
	private String addedDate;
	
	public int getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
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
	public String getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}
	
	public Wishlist(int wishlistId, int studentId, int courseId, int classId, String addedDate) {
		super();
		this.wishlistId = wishlistId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.classId = classId;
		this.addedDate = addedDate;
	}
	
	public Wishlist() {
		super();
	}
	
	@Override
	public String toString() {
		return "Wishlist [wishlistId=" + wishlistId + ", studentId=" + studentId + ", courseId=" + courseId
				+ ", classId=" + classId + ", addedDate=" + addedDate + "]";
	}

}
