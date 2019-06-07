package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet implementation class redirect
 */
@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("user", request.getUserPrincipal());
		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//List<Report> reports = request.getParameter("reportList");
		List<Report> reports = (List<Report>) request.getAttribute("reportList");
		String type = request.getParameter("order");
		
		if(reports == null) {
			//redirect
		}
		
		if(type.equals("categories")) {
			
		} else if(type.equals("date")) {
			
		} else {
			
		}
	}

}
