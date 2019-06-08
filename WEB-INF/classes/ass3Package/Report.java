/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Report Object
*/

package ass3Package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	private User author;
    private String reportContent;
    private String title;
    private String type;
    private String status;
    private String resolutionDetails;
    private int reportid;
    private Boolean inKnowledge;
    
    private LocalTime time;
    private LocalDate date;
    
    private LocalTime timeResolved;
    private LocalDate dateResolved;
    
    // Zero Arg Constructor
    Report(){

    }
    
    
	// Set Author
    public void setAuthor(User author){
        this.author = author;
    }

    // Get Author
    public User getAuthor(){
        return author;
    }
    
    // Get time
	public LocalTime getTime() {
		return time;
	}
	
	// Set time
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	// Get date
	public LocalDate getDate() {
		return date;
	}
	
	// Get date
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	// Get report content
	public String getReportContent() {
		return reportContent;
	}
	
	// Set report content
    public void setReportContent(String content) {
    	this.reportContent = content;
    }
    
    // Get report title
	public String getTitle() {
		return title;
	}
	
	// Set report title
	public void setTitle(String title) {
		this.title = title;
	}
	
	// Get report type
	public String getType() {
		return type;
	}
	
	// Set report type
	public void setType(String type) {
		this.type = type;
	}
	
	// Get report id
	public int getReportid() {
		return reportid;
	}
	
	// Set report id
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	
	// Get report status
    public String getStatus() {
		return status;
	}
    
    // Set report status
	public void setStatus(String status) {
		this.status = status;
	}
	
	// Get if report is in knowledge base
	public Boolean getInKnowledge() {
		return inKnowledge;
	}
	
	// Set if report is in knowledge base
	public void setInKnowledge(Boolean inKnowledge) {
		this.inKnowledge = inKnowledge;
	}
	
	// Get time report was resolved
	public LocalTime getTimeResolved() {
		return timeResolved;
	}
	
	// Set time report was solved
	public void setTimeResolved(LocalTime timeResolved) {
		this.timeResolved = timeResolved;
	}
	
	// Get date resolved
	public LocalDate getDateResolved() {
		return dateResolved;
	}
	
	// Set date resolved
	public void setDateResolved(LocalDate dateResolved) {
		this.dateResolved = dateResolved;
	}
	
	// Get resolution details
	public String getResolutionDetails() {
		return resolutionDetails;
	}
	
	// Set resolution details
	public void setResolutionDetails(String resolutionDetails) {
		this.resolutionDetails = resolutionDetails;
	}

	@Override
	public String toString() {
		return "Report [author=" + author + ", reportContent=" + reportContent + ", title=" + title + ", type=" + type
				+ ", status=" + status + ", reportid=" + reportid + ", inKnowledge=" + inKnowledge + ", time=" + time
				+ ", date=" + date + ", timeResolved=" + timeResolved + ", dateResolved=" + dateResolved + ", comments="
				+ "]";
	}
	
	//TODO clean methods up

	// Database
    // Return all reports as a list
	public static List<Report> getAllReports(){		
		String query = "SELECT * FROM reports";
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		try { 
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			
			result = con.createStatement().executeQuery(query);
			
			while(result.next()){ //step 5
				reports.add(0, Database.reportResult(result));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Get user reports
	public static List<Report> getUserReports(String userID){
		PreparedStatement ps = null;
		
		
		//String query = "SELECT * FROM reports WHERE = \'" + userID + "\'";
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		try { 
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			ps = con.prepareStatement("SELECT * FROM reports WHERE UserID = ?");
			ps.setString(1, userID);
			result = ps.executeQuery();
			//result = con.createStatement().executeQuery(query);
			
			while(result.next()){ //step 5
				reports.add(0, Database.reportResult(result));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Get knowledge base reports
	public static List<Report> getKnowledgeReports(){
		PreparedStatement ps = null;
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		try { 
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			ps = con.prepareStatement("SELECT * FROM reports WHERE inKnowledge = ?");
			ps.setBoolean(1, true);
			result = ps.executeQuery();
			//result = con.createStatement().executeQuery(query);
			
			while(result.next()){ //step 5
				reports.add(0, Database.reportResult(result));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Search for reports in knowledge base
	public static List<Report>	searchReports(String keyword){
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		try { 
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			
			System.out.println("LOG: Searching for: " + keyword);
			
			keyword = keyword.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
			ps = con.prepareStatement( "SELECT * FROM reports WHERE ReportContent LIKE ? OR Title LIKE ?");
			
			ps.setString(1,"%" + keyword + "%");
			ps.setString(2,"%" + keyword + "%");
			System.out.println("TESTING: " + ps.toString());
			result = ps.executeQuery();
			
			while(result.next()) { //step 5
				reports.add(0, Database.reportResult(result));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Search for reports in knowledge base
	public static List<Report>	searchReportsInKnowledge(String keyword){
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		try { 
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			
			System.out.println("LOG: Searching for: " + keyword);
			
			keyword = keyword.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
			ps = con.prepareStatement( "SELECT * FROM reports WHERE ( ReportContent LIKE ? OR Title LIKE ? ) AND inKnowledge = ?");
			
			ps.setString(1,"%" + keyword + "%");
			ps.setString(2,"%" + keyword + "%");
			ps.setBoolean(3, true);
			result = ps.executeQuery();
			
			while(result.next()) { //step 5
				reports.add(0, Database.reportResult(result));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Search for reports in knowledge base
		public static List<Report>	searchReportsUser(String keyword, String userid){
			List<Report> reports = new LinkedList<>();
			Connection con = null;
			ResultSet result = null;
			PreparedStatement ps = null;
			try { 
				con = Config.getConnection();
				// Check table exists
				if(!(Database.checkTableExists("reports"))){
					createReportTable();
				}
				
				System.out.println("LOG: Searching for: " + keyword);
				
				keyword = keyword.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
				ps = con.prepareStatement( "SELECT * FROM reports WHERE ( ReportContent LIKE ? OR Title LIKE ? ) AND UserID = ?");
				
				ps.setString(1,"%" + keyword + "%");
				ps.setString(2,"%" + keyword + "%");
				ps.setString(3, userid);
				result = ps.executeQuery();
				
				while(result.next()) { //step 5
					reports.add(0, Database.reportResult(result));
				}
			}
			catch(Exception e){
				System.err.println(e.getMessage());
				System.err.println(e.getStackTrace());
			}
			finally {
				try { con.close(); } catch (Exception e) { /* ignored */ }
				try { result.close(); } catch (Exception e) { /* ignored */ }
				try { ps.close(); } catch (Exception e) { /* ignored */ }
			}
			return reports;
			
		}
	
	// Add a new report to database
	public static void addReport(String uid, String title, String reportContent, String type, String status) {
		Connection con = null;
		PreparedStatement ps = null;
		Time tempTime = Time.valueOf(LocalTime.now());
		Date tempDate = Date.valueOf(LocalDate.now());
		
		Integer reportid = Database.max("reports", "ReportID");
		reportid++;
		
		try {
			con = Config.getConnection();
			ps = con.prepareStatement("INSERT INTO reports VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, reportid);
			ps.setString(2, uid);
			ps.setString(3, title);
			ps.setString(4, reportContent);
			ps.setString(5, type);
			ps.setTime(6, tempTime);
			ps.setDate(7, tempDate);
			ps.setString(8, status);
			ps.setBoolean(9, false);
			ps.setTime(10, null);
			ps.setDate(11, null);
			ps.setString(12, null);
			ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { ps.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Create table in database for reports
	public static void createReportTable() {
		String varNames[] = {"ReportID", "UserID", "Title", "ReportContent", "Type", "Time", "Date", "Status", "inKnowledge", "TimeResolved", "DateResolved", "ResolutionDetails"};
		String varType[] = {"INT", "VARCHAR(80)", "VARCHAR(80)", "VARCHAR(1000)", "VARCHAR(80)", "TIME", "DATE", "VARCHAR(80)", "BOOLEAN", "TIME", "DATE", "VARCHAR(500)"};
		
		Database.createTableString("reports", varNames, varType);
	}
}
