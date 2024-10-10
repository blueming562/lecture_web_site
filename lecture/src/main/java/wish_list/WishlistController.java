package wish_list;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/wishlist")
public class WishlistController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		WishlistDAO dao = new WishlistDAO();
		ArrayList<ClassInfo> classInfoList = dao.getClassInfoList();
		ArrayList<Wish> wishList = dao.getWishListByStudentId(1);
		int totalCredit = dao.getTotalCreditByStudentId(1);
		
		req.setAttribute("classInfoList", classInfoList);
		req.setAttribute("wishList", wishList);
		req.setAttribute("totalCredit", totalCredit);
		
		req.getSession().setAttribute("id", "1");
		
		req.getRequestDispatcher("WEB-INF/views/wishlist/wishlist.jsp").forward(req, resp);
		
	}

}
