package ass3Package;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String state = request.getParameter("state");
		Boolean knowledge = Boolean.parseBoolean(request.getParameter("knowledge"));
		int reportID = Integer.parseInt(request.getParameter("reportID"));
		
		System.out.println("Editing Report: " + reportID);
		
		if(state != null && knowledge != null) {
			Database.updateWhere("reports", "Status", state, "ReportID", reportID);
			Database.updateWhere("reports", "inKnowledge", knowledge, "ReportID", reportID);
		}else if(state == null && knowledge != null) {
			Database.updateWhere("reports", "inKnowledge", knowledge, "ReportID", reportID);
		} else {
			System.out.println("ERROR NULL");
		}
		
		request.getRequestDispatcher("/ViewReports").forward(request, response); //redirect to main page
		
	}

}
