package ass3Package;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("submit");
		if (submit.equals("ADD")) {
			String title = request.getParameter("title");
			//int year  = Integer.parseInt(request.getParameter("year"));;
			//String url = request.getParameter("url"); 

			System.out.println("LOG: Adding new entry to db");
			Report.addReport(title);
		} 
		else if (submit.equals("DELETE")) {
			String title = request.getParameter("title");
			System.out.println("LOG: Removing entry");
			Database.deleteEntry(title, "movie");
		} 
		else if (submit.equals("REMOVE TABLE")) {
			System.out.println("LOG: Removing table");
			Database.removeTable("movie");
		}
		
		submit = null;
		request.setAttribute("reports", Report.getAllReports());
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/Reports.jsp").forward(request, response);
	}
}