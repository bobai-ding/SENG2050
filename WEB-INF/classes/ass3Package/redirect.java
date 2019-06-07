package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

/**
 * Servlet implementation class redirect
 */
@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("user", request.getUserPrincipal());
		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//List<Report> reports = request.getParameter("reportList");
		List<Object> reports =  (List<Object>) request.getAttribute("reportList");
		String type = request.getParameter("order");
		
		Object[] arr = null;
		
		//System.out.println("SORTING | Reports = " + reports + " | type = " + type);
		
		if(reports == null) {
			//redirect
			request.setAttribute("user", request.getUserPrincipal());
			request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
		} else {
			arr = (Object[]) reports.toArray();
		}
		
		System.out.println("SORTING | Reports = " + reports + " | type = " + type + " | arr = " + arr);
		
		if(type.equals("categories")) {
			String temp = "";
			String A = "";
			String B = "";
			for(int i = 0; i < arr.length ;i++) {
				for(int j = 0; j < (arr.length - 1);j++) {
					A = ((Report) arr[j]).getType();
					B = ((Report) arr[j+1]).getType();
					if(A.compareTo(B) > 0) {
						temp = A;
						A = B;
						B = temp;
					}
				}
			}
		} else if(type.equals("date")) {
			LocalDate temp = null;
			LocalDate A = null;
			LocalDate B = null;
			for(int i = 0; i < arr.length ;i++) {
				for(int j = 0; j < (arr.length - 1);j++) {
					A = ((Report) arr[j]).getDate();
					B = ((Report) arr[j+1]).getDate();
					if(A.compareTo(B) > 0) {
						temp = A;
						A = B;
						B = temp;
					}
				}
			}
		} else { //sort by status
			String temp = "";
			String A = "";
			String B = "";
			for(int i = 0; i < arr.length ;i++) {
				for(int j = 0; j < (arr.length - 1);j++) {
					A = ((Report) arr[j]).getStatus();
					B = ((Report) arr[j+1]).getStatus();
					if(A.compareTo(B) > 0) {
						temp = A;
						A = B;
						B = temp;
					}
				}
			}
		}
		
		reports = Arrays.asList(arr);
		request.setAttribute("reports", reports);
		request.getRequestDispatcher(request.getParameter("sender")).forward(request, response); //redirect to main page
		
	}

}
