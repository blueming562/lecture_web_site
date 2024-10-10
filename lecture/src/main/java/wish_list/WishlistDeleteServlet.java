package wish_list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wishlist/delete")
public class WishlistDeleteServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int classId = Integer.parseInt(req.getParameter("classId"));
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		
		WishlistDAO dao = new WishlistDAO();
		
		int rRow = dao.removeFromWishList(classId, studentId);
		
		resp.getWriter().print(rRow);
		
	}

}
