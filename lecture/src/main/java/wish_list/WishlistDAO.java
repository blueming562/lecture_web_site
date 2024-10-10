package wish_list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishlistDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:testdb";
	String user = "scott";
	String password = "tiger";
	
	private Connection dbCon() {
		
		Connection con = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	private void dbClose(AutoCloseable ...autoCloseables) {
		
		for(AutoCloseable a : autoCloseables) {
			
			if(a != null) {
				try {
					a.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	// 학생 id에 해당하는 wishlist
	public ArrayList<Wish> getWishListByStudentId(int sid){
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = " select w.student_id, w.course_id, w.class_id, w.added_date, "
				+ " co.course_name, co.department_id, co.classification, co.semester, co.credit, "
				+ " cl.professor_id, cl.room_no, cl.capacity, cl.enrolled, cl.day_of_week, cl.start_time, cl.end_time, "
				+ " d.department_name, p.name "
				+ " from wishlist w "
				+ " join course co "
				+ " on w.course_id = co.course_id "
				+ " join class cl "
				+ " on w.course_id = cl.course_id and w.class_id = cl.class_id "
				+ " join department d "
				+ " on co.department_id = d.department_id "
				+ " join professor p "
				+ " on cl.professor_id = p.professor_id "
				+ " where student_id = ? "
				+ " order by w.class_id ";
		
		ArrayList<Wish> list = new ArrayList<>();
		
		try {
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				int studentId = rs.getInt(1);
				int courseId = rs.getInt(2);
				int classId = rs.getInt(3);
				String addedDate = rs.getString(4);
				String courseName = rs.getString(5);
				int departmentId = rs.getInt(6);
				String classification = rs.getString(7);
				String semester = rs.getString(8);
				int credit = rs.getInt(9);
				int professorId = rs.getInt(10);
				String roomNo = rs.getString(11);
				int capacity = rs.getInt(12);
				int enrolled = rs.getInt(13);
				String dayOfWeek = rs.getString(14);
				String startTime = rs.getString(15);
				String endTime = rs.getString(16);
				String departmentName = rs.getString(17);
				String professorName = rs.getString(18);
				
				Wish wishlist = new Wish(studentId, courseId, classId, addedDate,
						courseName, departmentId, classification, semester, credit,
						professorId, roomNo, capacity, enrolled, dayOfWeek, startTime, endTime,
						departmentName, professorName);
				list.add(wishlist);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst, rs);
		}
		
		return list;
		
	}
	
	// 모든 강의 정보
	public ArrayList<ClassInfo> getClassInfoList(){
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = " select co.course_id, cl.class_id, co.course_name, co.department_id, co.classification, co.semester, co.credit, "
				+ " cl.professor_id, cl.room_no, cl.capacity, cl.enrolled, cl.day_of_week, cl.start_time, cl.end_time, "
				+ " d.department_name, p.name "
				+ " from course co "
				+ " join class cl "
				+ " on co.course_id = cl.course_id "
				+ " join department d "
				+ " on co.department_id = d.department_id "
				+ " join professor p "
				+ " on cl.professor_id = p.professor_id ";
		
		ArrayList<ClassInfo> list = new ArrayList<>();
		
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				int courseId = rs.getInt(1);
				int classId = rs.getInt(2);
				String courseName = rs.getString(3);
				int departmentId = rs.getInt(4);
				String classification = rs.getString(5);
				String semester = rs.getString(6);
				int credit = rs.getInt(7);
				int professorId = rs.getInt(8);
				String roomNo = rs.getString(9);
				int capacity = rs.getInt(10);
				int enrolled = rs.getInt(11);
				String dayOfWeek = rs.getString(12);
				String startTime = rs.getString(13);
				String endTime = rs.getString(14);
				String departmentName = rs.getString(15);
				String professorName = rs.getString(16);
				
				ClassInfo classInfo = new ClassInfo(courseId, classId, courseName, departmentId, classification, semester, credit,
						professorId, roomNo, capacity, enrolled, dayOfWeek, startTime, endTime,
						departmentName, professorName);
				
				list.add(classInfo);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst, rs);
		}
		
		return list;
		
	}
	
	// wishlist 총 학점
	public int getTotalCreditByStudentId(int sid) {
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = " select sum(credit) "
				+ " from wishlist w "
				+ " join course co "
				+ " on w.course_id = co.course_id "
				+ " where w.student_id = ? ";
		
		int totalCredit = 0;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				totalCredit = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst, rs);
		}
		
		return totalCredit;
		
	}
	
	// 강의 id로 강의 정보 찾기
	public ClassInfo getClassInfoByClassId(int cid) {
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = " select co.course_id, cl.class_id, co.course_name, co.department_id, co.classification, co.semester, co.credit, "
				+ " cl.professor_id, cl.room_no, cl.capacity, cl.enrolled, cl.day_of_week, cl.start_time, cl.end_time, "
				+ " d.department_name, p.name "
				+ " from course co "
				+ " join class cl "
				+ " on co.course_id = cl.course_id "
				+ " join department d "
				+ " on co.department_id = d.department_id "
				+ " join professor p "
				+ " on cl.professor_id = p.professor_id "
				+ " where cl.class_id = ? ";
		
		ClassInfo classInfo = null;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				int courseId = rs.getInt(1);
				int classId = rs.getInt(2);
				String courseName = rs.getString(3);
				int departmentId = rs.getInt(4);
				String classification = rs.getString(5);
				String semester = rs.getString(6);
				int credit = rs.getInt(7);
				int professorId = rs.getInt(8);
				String roomNo = rs.getString(9);
				int capacity = rs.getInt(10);
				int enrolled = rs.getInt(11);
				String dayOfWeek = rs.getString(12);
				String startTime = rs.getString(13);
				String endTime = rs.getString(14);
				String departmentName = rs.getString(15);
				String professorName = rs.getString(16);
				
				classInfo = new ClassInfo(courseId, classId, courseName, departmentId, classification, semester, credit,
						professorId, roomNo, capacity, enrolled, dayOfWeek, startTime, endTime,
						departmentName, professorName);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst, rs);
		}
		
		return classInfo;
	}
	
	public int addToWishList(Wish wish) {
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		
		String sql = " insert into wishlist(student_id, course_id, class_id, added_date) "
				+ " values(?, ?, ?, to_date(?, 'YYYY-MM-DD HH24:MI:SS')) ";
		
		int rRow = 0;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, wish.getStudentId());
			pst.setInt(2, wish.getCourseId());
			pst.setInt(3, wish.getClassId());
			pst.setString(4, wish.getAddedDate());
			
			rRow = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst);
		}
		
		return rRow;
		
	}
	
	public int removeFromWishList(int classId, int studendId) {
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		
		String sql = "delete from wishlist where class_id = ? and student_id = ?";
		
		int rRow = 0;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, classId);
			pst.setInt(2, studendId);
			
			rRow = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(con, pst);
		}
		
		return rRow;
		
	}
	
	public static void main(String[] args) {
		WishlistDAO dao = new WishlistDAO();
		ArrayList<Wish> list = dao.getWishListByStudentId(1);
		System.out.println(list);
		ArrayList<ClassInfo> list2 = dao.getClassInfoList();
		System.out.println(list2);
		int totalCredit = dao.getTotalCreditByStudentId(1);
		System.out.println(totalCredit);
		ClassInfo classInfo = dao.getClassInfoByClassId(18);
		System.out.println(classInfo);
	}

}
