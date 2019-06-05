package ass3Package;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("submit");
		if (submit != null) {
			if (submit.equals("ADD")) {
				String uid = request.getParameter("uid");
				String content = request.getParameter("content");
				System.out.println("LOG: Adding new entry under UserID: " + uid);
				Report.addReport(uid, content);
			} 
			else if (submit.equals("DELETE")) {
				String uid = request.getParameter("uid");
				System.out.println("LOG: Removing entry under UserID: " + uid);
				Database.deleteEntry(uid, "UserID", "reports");
			} 
			else if (submit.equals("REMOVE TABLE")) {
				System.out.println("LOG: Removing table");
				Database.removeTable("reports");
			}
		} else {
			request.setAttribute("hideIfUserIsNotFound", "hidden");
		}
		
		submit = null;
		request.setAttribute("reports", Report.getAllReports());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/Reports.jsp").forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}