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
		//List<Object> reports =  (List<Object>) request.getAttribute("reportList");
		
		List<Report> reports = null;
		
		if(request.isUserInRole("staff")) {
			reports = Report.getAllReports();
		} else {
			reports = Report.getUserReports(request.getParameter("user"));
		}
		
		String type = request.getParameter("order");
		
		Object[] arr = null;
		
		//System.out.println("SORTING | Reports = " + reports + " | type = " + type);
		
		if(reports == null) {
			//redirect
			request.setAttribute("user", request.getUserPrincipal());
			request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
		} else {
			arr = reports.toArray();
		}
		
		//System.out.println("SORTING | Reports = " + reports + " | type = " + type + " | arr = " + arr);
		System.out.println("SORTING | type = " + type + " | arr = " + arr);
		//System.out.println("BEFORE: " + reports + "\r\n\r\n\r\n*****************************************************************************");
		/*
		for(int i = 0; i < arr.length;i++) {
			System.out.println("i = " + i + " | val = " + arr[i]);
		}
		*/
		
		Report tempRep = null;
		Report first = null;
		Report second = null;
				
		if(type.equals("categories")) {
			String temp = "";
			String A = "";
			String B = "";
			for(int i = 0; i < arr.length ;i++) {
				for(int j = 0; j < (arr.length - 1);j++) {
					A = ((Report) arr[j]).getType();
					B = ((Report) arr[j+1]).getType();
					if(A.compareTo(B) > 0) {
						//first = ((Report) arr[j]);
						//second = ((Report) arr[j+1]);
						
						tempRep = ((Report) arr[j]);
						first = ((Report) arr[j+1]);
						second = tempRep;
						
						arr[j] = first;
						arr[j+1] = second;								
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
					//System.out.println("A = " + A + " | B = " + B + " | i = " + i + " | J = " + j);
					if(A.equals(B)) {
						LocalTime At = ((Report) arr[j]).getTime();
						LocalTime Bt = ((Report) arr[j+1]).getTime();
						LocalTime tempt = null;
						if(At.compareTo(Bt) < 0) {
							tempRep = ((Report) arr[j]);
							first = ((Report) arr[j+1]);
							second = tempRep;
							
							arr[j] = first;
							arr[j+1] = second;	
						}
					}else {
						if(A.compareTo(B) < 0) {
							tempRep = ((Report) arr[j]);
							first = ((Report) arr[j+1]);
							second = tempRep;
							
							arr[j] = first;
							arr[j+1] = second;	
						}
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
					//System.out.println("A = " + A + " | B = " + B + " | i = " + i + " | J = " + j);
					if(A.compareTo(B) < 0) {
						tempRep = ((Report) arr[j]);
						first = ((Report) arr[j+1]);
						second = tempRep;
						
						arr[j] = first;
						arr[j+1] = second;	
					}
				}
			}
		}
		
		List<Object> ordered = Arrays.asList(arr);
		
		//System.out.println("After: " + ordered + "\r\n\r\n\r\n*****************************************************************************");
		request.setAttribute("reports", ordered);
		request.setAttribute("user", request.getUserPrincipal());
		request.getRequestDispatcher(request.getParameter("sender")).forward(request, response); //redirect to main page
		
	}

}
