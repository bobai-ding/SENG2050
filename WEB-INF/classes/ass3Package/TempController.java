package ass3Package;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class TempController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String submit = request.getParameter("submit");
		String uid = request.getParameter("uid");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String comment = request.getParameter("comment");
		int reportid = 0;
		
		String dispatchLocation = "/WEB-INF/jsp/Reports.jsp";
		
		if (submit != null) {
			if (request.getParameter("reportid") != null)  reportid = Integer.parseInt(request.getParameter("reportid"));
			if (submit.equals("ADD")) {
				System.out.println("LOG: Adding new entry under UserID: " + uid + " with Title: " + title);
				Report.addReport(uid, title, content, type);
			} 
			else if (submit.equals("DELETE")) {
				System.out.println("LOG: Removing entry under ReportID: " + reportid);
				Database.deleteEntry(reportid, "ReportID", "reports");
			} 
			else if (submit.equals("REMOVE TABLE")) {
				System.out.println("LOG: Removing table");
				Database.removeTable("reports");
			}
			else if (submit.equals("OPEN")) {
				System.out.println("LOG: Opening entry under ReportID: " + reportid);
				request.setAttribute("specificReport", Database.viewSpecificReport(reportid));
				dispatchLocation = "/WEB-INF/jsp/ViewReport.jsp";
			}
			else if (submit.equals("OPEN")) {
				System.out.println("LOG: Opening entry under ReportID: " + reportid);
				request.setAttribute("specificReport", Database.viewSpecificReport(reportid));
				
				// Will be a list of comments
				//request.setAttribute("comments", Database.getComments(ReportID));
				
				dispatchLocation = "/WEB-INF/jsp/ViewReport.jsp";
			}
			else if (submit.equals("ADD COMMENT")) {
				// Need reportid
				// Need message contents
				// Need UserID
				
				System.out.println("LOG: Adding comment for ReportID: " + reportid);
				Comment.addComment(reportid, comment, uid, false);
				dispatchLocation = "/WEB-INF/jsp/ViewReport.jsp";
			}
		}
		
		submit = null;
		request.setAttribute("reports", Report.getAllReports());
		getServletContext().getRequestDispatcher(dispatchLocation).forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}