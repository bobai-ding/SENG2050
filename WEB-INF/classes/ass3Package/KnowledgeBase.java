/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Knowledge base servlet
*/

package ass3Package;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KnowledgeBase")
public class KnowledgeBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Temp vals
		String submit = request.getParameter("submit");
		String searchVal = request.getParameter("searchVal");
		String dispactchLocation = "/WEB-INF/jsp/KnowledgeBase.jsp";
		//List<Report> tempReports = null;
		
		// Search Database
		System.out.println("LOG: Searching for "+submit);
		if (submit != null) {
			if (submit.equals("SEARCH")) {
				Report.searchReports(searchVal);
				request.setAttribute("reports", Report.searchReportsInKnowledge(searchVal));
			} else {
				request.setAttribute("reports", Report.getKnowledgeReports());
			}
		} else {
			request.setAttribute("reports", Report.getKnowledgeReports());
		}
		
		// Load page
		request.getRequestDispatcher(dispactchLocation).forward(request, response); //redirect to main page
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
