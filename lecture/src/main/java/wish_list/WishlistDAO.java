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
	public ArrayList<Wishlist> selectAllByStudentId(int sid){
		
		Connection con = dbCon();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select * from wishlist where student_id = ?";
		
		ArrayList<Wishlist> list = new ArrayList<>();
		
		try {
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, sid);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				int wishlistId = rs.getInt(1);
				int studentId = rs.getInt(2);
				int courseId = rs.getInt(3);
				int classId = rs.getInt(4);
				String addedDate = rs.getString(5);
				
				Wishlist wishlist = new Wishlist(wishlistId, studentId, courseId, classId, addedDate);
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
	
//	public static void main(String[] args) {
//		WishlistDAO dao = new WishlistDAO();
//		ArrayList<Wishlist> list = dao.selectAllByStudentId(1);
//		System.out.println(list);
//	}

}
