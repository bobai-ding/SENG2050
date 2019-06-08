/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Temp (Not really) Controller servlet
*/

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
		String uid = request.getUserPrincipal().getName();
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
				// TODO Set status properly
				Report.addReport(uid, title, content, type, "temp");
			} 
			else if (submit.equals("DELETE")) {
				System.out.println("LOG: Removing entry under ReportID: " + reportid);
				// Delete report from reports table
				Database.deleteEntry(reportid, "ReportID", "reports");
				// Delete associated comments from comments table
				Database.deleteEntry(reportid, "ReportID", "comments");
			} 
			else if (submit.equals("REMOVE TABLE")) {
				System.out.println("LOG: Removing table");
				Database.removeTable("reports");
				Database.removeTable("comments");
			}
			else if (submit.equals("OPEN")) {
				System.out.println("LOG: Opening entry under ReportID: " + reportid);
				request.setAttribute("specificReport", Database.viewSpecificReport(reportid));
				request.setAttribute("comments", Comment.getSpecificComments(reportid));
				
				dispatchLocation = "/WEB-INF/jsp/ViewReport.jsp";
			}
			else if (submit.equals("ADD COMMENT")) {
				System.out.println("LOG: Adding comment for ReportID: " + reportid);
				Comment.addComment(reportid, comment, uid, false);
				
				request.setAttribute("specificReport", Database.viewSpecificReport(reportid));
				request.setAttribute("comments", Comment.getSpecificComments(reportid));
				
				dispatchLocation = "/WEB-INF/jsp/ViewReport.jsp";
			}
		}
		
		submit = null;
		request.setAttribute("reports", Report.getAllReports());
		request.setAttribute("user", request.getUserPrincipal());
		request.setAttribute("userReports", Report.getUserReports(uid));
		System.out.println("user id: " + uid + " is getting their reports");
		
		getServletContext().getRequestDispatcher(dispatchLocation).forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", request.getUserPrincipal());
		request.setAttribute("reports", Report.getAllReports());
		getServletConfig().getServletContext().setAttribute("user", request.getUserPrincipal());

		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
	}
}