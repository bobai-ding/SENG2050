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
		if (submit.equals("ADD")) {
			String uid = request.getParameter("uid");
			System.out.println("LOG: Adding new entry to db with UserID: " + uid);
			Report.addReport(uid);
		} 
		else if (submit.equals("DELETE")) {
			String title = request.getParameter("title");
			System.out.println("LOG: Removing entry");
			Database.deleteEntry(title, "reports");
		} 
		else if (submit.equals("REMOVE TABLE")) {
			System.out.println("LOG: Removing table");
			Database.removeTable("reports");
		}
		
		submit = null;
		request.setAttribute("reports", Report.getAllReports());
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/Reports.jsp").forward(request, response);
	}
}