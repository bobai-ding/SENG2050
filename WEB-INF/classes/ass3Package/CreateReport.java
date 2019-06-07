package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateReport
 */
@WebServlet("/CreateReport")
public class CreateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/jsp/user/CreateReport.jsp").forward(request, response); //redirect to main page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.getWriter().append("create report served at: ").append(request.getContextPath());
		request.setAttribute("user", request.getUserPrincipal());
		String submit = request.getParameter("submit");
		String uid = request.getUserPrincipal().getName();
		String content = request.getParameter("content");
		String type = request.getParameter("category");
		String title = request.getParameter("title");
		String comment = request.getParameter("comment");
		String status = request.getParameter("status");
		int reportid = 0;
		
		String dispatchLocation = "/WEB-INF/jsp/Reports.jsp";
		
		if (submit != null) {
			if (request.getParameter("reportid") != null)  reportid = Integer.parseInt(request.getParameter("reportid"));
		
			System.out.println("LOG: Adding new entry under UserID: " + uid + " with Title: " + title);
			System.out.println("LOG: Adding new entry under UserID: " + uid + " with Title: " + title);
			// TODO Set status properly
			Report.addReport(uid, title, content, type, "new");
			
		} else {
			dispatchLocation = "/WEB-INF/jsp/user/Main.jsp";
		}
		
		request.setAttribute("reports", Report.getUserReports(uid));
		
		getServletContext().getRequestDispatcher(dispatchLocation).forward(request, response);
		//TODO create report from data recieved from jsp and forward to viewReport.jsp
	}

}
