package wish_list;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wishlist/add")
public class WishlistAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int classId = Integer.parseInt(req.getParameter("classId"));
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		
		WishlistDAO dao = new WishlistDAO();
		
		ClassInfo classInfo = dao.getClassInfoByClassId(classId);
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedDate = format.format(now);
		
		Wish wish = new Wish(studentId, classInfo.getCourseId(), classInfo.getClassId(), addedDate, classInfo.getCourseName(),
				classInfo.getDepartemntId(), classInfo.getClassification(), classInfo.getSemester(), classInfo.getCredit(),
				classInfo.getProfessorId(), classInfo.getRoomNo(), classInfo.getCapacity(), classInfo.getEnrolled(),
				classInfo.getDayOfWeek(), classInfo.getStartTime(), classInfo.getEndTime(), classInfo.getDepartmentName(),
				classInfo.getProfessorName());
		
		int rRow = dao.addToWishList(wish);
		
		resp.getWriter().print(rRow);
		
	}

}
