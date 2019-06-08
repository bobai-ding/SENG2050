/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Redirect servlet
*/

package ass3Package;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Arrays;

@WebServlet("/redirect")
public class redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("user", request.getUserPrincipal());
		request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean inKnowledge = Boolean.parseBoolean(request.getParameter("knowledge")); //get boolean to determine if called from knowledge base
		
		List<Report> reports = null; //declare variable
		
		if(inKnowledge) { //if calleb by knowledge base
			reports = Report.getKnowledgeReports(); //get all knowledge base reports
		} else { //not knowledge base
				
			if(request.isUserInRole("staff")) { //check if user is staff
				reports = Report.getAllReports(); //get all reports
			} else { // regular user
				reports = Report.getUserReports(request.getParameter("user")); //get the user's reports
			}
		}
		
		String type = request.getParameter("order"); //get parameter on how to sort
		
		Object[] arr = null; //declare array
		
		if(reports == null) { //check if reports was populated
			//redirect
			request.setAttribute("user", request.getUserPrincipal()); //set user
			request.getRequestDispatcher("/WEB-INF/jsp/user/Main.jsp").forward(request, response); //redirect to main page
		} else { //reports we're fine
			arr = reports.toArray(); //convert list to array
		}
		
		
		System.out.println("SORTING | type = " + type + " | arr = " + arr);
				
		//temporary variables used for sorting
		
		Report tempRep = null; 
		Report first = null;
		Report second = null;
				
		if(type.equals("categories")) { //check if needed to be sorted by category
			
			//temp variables for checking order			
			String temp = "";
			String A = "";
			String B = "";
			
			for(int i = 0; i < arr.length ;i++) { //loop thorugh array
				for(int j = 0; j < (arr.length - 1);j++) { //bubble through array
					A = ((Report) arr[j]).getType(); //get current pos
					B = ((Report) arr[j+1]).getType(); //get next pos
					if(A.compareTo(B) > 0) { //compare
												
						tempRep = ((Report) arr[j]); //swap two reports
						first = ((Report) arr[j+1]);
						second = tempRep;
						
						arr[j] = first; //save report to array
						arr[j+1] = second;  //save report to array								
					}
				}
			}
			
		} else if(type.equals("date")) { //check if needed to be sorted by date
			//temp variables for checking order	
			LocalDate temp = null;
			LocalDate A = null;
			LocalDate B = null;
			
			for(int i = 0; i < arr.length ;i++) { //loop thorugh array
				for(int j = 0; j < (arr.length - 1);j++) { //bubble through array
					A = ((Report) arr[j]).getDate(); //get current pos (date)
					B = ((Report) arr[j+1]).getDate(); //get next pos (date)
					
					if(A.equals(B)) { //check if the dates match
						//dates match
						LocalTime At = ((Report) arr[j]).getTime(); //get current pos (time)
						LocalTime Bt = ((Report) arr[j+1]).getTime(); //get next pos (time)
						LocalTime tempt = null; //temporary variable
						if(At.compareTo(Bt) > 0) { //compare times
							tempRep = ((Report) arr[j]); //swap report positions
							first = ((Report) arr[j+1]);
							second = tempRep;
							
							arr[j] = first; //save report to array
							arr[j+1] = second;	//save report to array
						}
					} else { // dates did not match
						if(A.compareTo(B) > 0) { //compare dates
							tempRep = ((Report) arr[j]); //swap report positions
							first = ((Report) arr[j+1]);
							second = tempRep;
							
							arr[j] = first; //save report to array
							arr[j+1] = second;	 //save report to array
						}
					}
				}
			}
		} else { //sort by status
			//temporary variables
			String temp = "";
			String A = "";
			String B = "";
			
			for(int i = 0; i < arr.length ;i++) { //loop thorugh array
				for(int j = 0; j < (arr.length - 1);j++) { //bubble through array
					A = ((Report) arr[j]).getStatus(); //get current pos
					B = ((Report) arr[j+1]).getStatus(); //get next pos
					
					if(checkStatus(A) > checkStatus(B)) { //compare order using function
						tempRep = ((Report) arr[j]); //swap report positions
						first = ((Report) arr[j+1]);
						second = tempRep;
						
						arr[j] = first; //save report to array
						arr[j+1] = second;	//save report to array
					}
				}
			}
		}
		
		List<Object> ordered = Arrays.asList(arr); //convert array to list
		
		request.setAttribute("reports", ordered); //pass list as attribute
		request.setAttribute("user", request.getUserPrincipal()); //pass user as attribute
		request.getRequestDispatcher(request.getParameter("sender")).forward(request, response); //redirect to page that called sort function
		
	}
	
	// Check status of report and return appropriate value new = 0 -> resolved = 4
	//returns -1 if does not match
	private int checkStatus(String status) {
		int out = -1; //set return variable
		
		//check if input string matches any status
		if(status.equals("new")) {out = 0;}
		if(status.equals("inProgress")) {out = 1;}
		if(status.equals("completed")) {out = 2;}
		if(status.equals("resolved")) {out = 3;}
		
		return out; //return value
	}

}
