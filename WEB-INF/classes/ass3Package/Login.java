/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Login Servlet
*/

package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("") // Mapped to empty url
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve Reports
		request.setAttribute("reports", Report.getAllReports());
		
		// Get UserID
		getServletConfig().getServletContext().setAttribute("user", request.getUserPrincipal());
		request.setAttribute("user", request.getUserPrincipal());
		
		// Used for a feedback message
		request.getSession().setAttribute("newReport", false);
		
		// Load page
		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
