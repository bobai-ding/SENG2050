package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewReports
 */
@WebServlet("/ViewReports")
public class ViewReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("view - role = staff: " + request.isUserInRole("staff") + " | name = " + request.getUserPrincipal().getName() + " | Principal = " + request.getUserPrincipal());
		
		
		if(request.isUserInRole("staff")) {
			request.setAttribute("reports", Report.getAllReports());
		} else {
			String temperoono = request.getUserPrincipal().getName();
			request.setAttribute("reports", Report.getUserReports(temperoono));
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("user", request.getUserPrincipal());
		request.getRequestDispatcher("/WEB-INF/jsp/Reports.jsp").forward(request, response); //redirect to main page
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.getWriter().append("View Reports served at: ").append(request.getContextPath());
		
		//TODO get reports and then forward to reports.jsp
	}

}
