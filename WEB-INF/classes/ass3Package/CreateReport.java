/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Create Report Servlet
*/

package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateReport")
public class CreateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/user/CreateReport.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get user name from jdbc realm
		request.setAttribute("user", request.getUserPrincipal());
		// Temp variables
		String submit = request.getParameter("submit");
		String uid = request.getUserPrincipal().getName();
		String content = request.getParameter("content");
		String type = request.getParameter("category");
		String title = request.getParameter("title");
		String dispatchLocation = "ViewReports";
		
		if (submit != null) {
			// Add a new report
			System.out.println("LOG: Adding new entry under UserID: " + uid + " with Title: " + title);
			Report.addReport(uid, title, content, type, "new");
			
			request.getSession().setAttribute("newReport", true);
			
		} else {
			dispatchLocation = "/WEB-INF/jsp/user/Main.jsp";
		}
		
		// If user is staff show all reports
		if(request.isUserInRole("staff")) {
			request.setAttribute("reports", Report.getAllReports());
		} 
		// Else Show specific users report
		else {
			request.setAttribute("reports", Report.getUserReports(uid));
		}
		
		// Redirect to next page 
		response.sendRedirect(dispatchLocation);
	}

}
