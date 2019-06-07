package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KnowledgeBase")
public class KnowledgeBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("submit");
		String searchVal = request.getParameter("searchVal");
		String dispactchLocation = "/WEB-INF/jsp/KnowledgeBase.jsp";
		
		System.out.println("TESTING: search val "+submit);
		if (submit != null) {
			if (submit.equals("SEARCH")) {
				System.out.println("TESTING TO TO SEARCH");
				Report.searchReports(searchVal);
				request.setAttribute("reports", Report.searchReports(searchVal));
			} else {
				request.setAttribute("reports", Report.getKnowledgeReports());
			}
		} else {
			request.setAttribute("reports", Report.getKnowledgeReports());
		}
		
		request.getRequestDispatcher(dispactchLocation).forward(request, response); //redirect to main page
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
