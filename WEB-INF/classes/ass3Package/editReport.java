/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Edit report servlet
*/

package ass3Package;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editReport
 */
@WebServlet("/editReport")
public class editReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Temp Values
		String state = request.getParameter("state");
		
		Boolean knowledge = Boolean.parseBoolean(request.getParameter("knowledge"));
		int reportID = Integer.parseInt(request.getParameter("reportID"));
		
		Time tempTime = null;
		Date tempDate = null;
		
		System.out.println("Editing Report: " + reportID);
		
		if(state != null && knowledge != null) {
			// Update report status And if in knowledge base
			Database.updateWhere("reports", "Status", state, "ReportID", reportID);
			Database.updateWhere("reports", "inKnowledge", knowledge, "ReportID", reportID);
			
			// If report is solved, add time solved
			if(state.equals("resolved")) {
				tempTime = Time.valueOf(LocalTime.now());
				tempDate = Date.valueOf(LocalDate.now());
				Database.updateWhere("reports", "TimeResolved", tempTime, "ReportID", reportID);
				Database.updateWhere("reports", "DateResolved", tempDate, "ReportID", reportID);
            }
		} else if(state == null && knowledge != null) {
			// Only update if in knowledge base
			Database.updateWhere("reports", "inKnowledge", knowledge, "ReportID", reportID);
		} else {
			System.out.println("ERROR NULL");
		}
		
		// Redirect to view reports
		response.sendRedirect("ViewReports");
		
	}

}
