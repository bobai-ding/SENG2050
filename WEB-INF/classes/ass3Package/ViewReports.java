/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: View Reports Servlet
*/

package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewReports")
public class ViewReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("view - role = staff: " + request.isUserInRole("staff") + " | name = " + request.getUserPrincipal().getName() + " | Principal = " + request.getUserPrincipal());
		String submit = request.getParameter("submit");
		
		if (submit != null) {
			if (submit.equals("REMOVE TABLE")) {
				// Delete tables from database
				System.out.println("LOG: Removing table");
				Database.removeTable("reports");
				Database.removeTable("comments");
			}
		}
		
		// If is staff show all reports
		if(request.isUserInRole("staff")) {
			request.setAttribute("reports", Report.getAllReports());
		} 
		// If is user show only their reports
		else {
			String temperoono = request.getUserPrincipal().getName();
			request.setAttribute("reports", Report.getUserReports(temperoono));
		}
		
		// Set attributes
		request.setAttribute("user", request.getUserPrincipal());
		request.getSession().setAttribute("newReport", false);
		
		// Forward to page
		request.getRequestDispatcher("/WEB-INF/jsp/Reports.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
