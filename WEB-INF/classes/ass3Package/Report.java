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
    private int reportid;
    private Boolean inKnowledge;
    
    private LocalTime time;
    private LocalDate date;

    private LinkedList<Comment> comments = new LinkedList<>();

    Report(){

    }

    public void newReport(String report, User author){

        //create a new report with a report string and user object

        time = LocalTime.now();
        date = LocalDate.now();
        this.reportContent = report;
        this.author = author;

        System.out.println(time);
        System.out.println(date);

        System.out.println(report);
        System.out.println(author.getUid());
    }

    public void editReport(String report){

        // TODO need to format the time to remove the ms
    	// Can use .withNano(0) to remove
    	
        //probs need to format the date to aus standards

    	setTime(LocalTime.now());
        setDate(LocalDate.now());
        this.reportContent = report;
    }

    public void newCommentObject(Comment comment){

        //create a new comment with a comment object

        //set the number of the comment to the total of the size of the array

        comment.setCommentPos(comments.size()+1);

        comments.add(comment);

    }

    public void newCommentString(String comment, User author){

        //create a new comment with a user object and a string

        Comment newComment = new Comment(comment, author);

        newComment.setCommentPos(comments.size()+1);

        comments.add(newComment);

    }

    public void editComment(int pos, String newComment) {
        //searches the comment linked list for a comment base on a position given

        for(Comment s : comments){
            if(s.getCommentPos() == pos){

                //when it finds the position, it changes the comment

                s.editComment(newComment);
            }
        }
    }

    // Getter / Setters
    public Comment[] getComments(){

        //returns all the current comments that a report has in an array

        Comment[] outputComments = new Comment[comments.size()];

        int i = 0;

        for(Comment s : comments){
            outputComments[i] = s;
            i++;
        }

        return outputComments;
    }

	public void setComments(LinkedList<Comment> comments) {
		this.comments = comments;
	}
    
    public void setAuthor(User author){
        this.author = author;
    }

    public User getAuthor(){
        return author;
    }
    
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getReportContent() {
		return reportContent;
	}

    public void setReportContent(String content) {
    	this.reportContent = content;
    }
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getReportid() {
		return reportid;
	}

	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Boolean getInKnowledge() {
		return inKnowledge;
	}

	public void setInKnowledge(Boolean inKnowledge) {
		this.inKnowledge = inKnowledge;
	}

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
				Report report = new Report();
				User user = new User();
				
				report.setReportid(result.getInt(1));
				user.setUid(result.getString(2));
				report.setAuthor(user);
				report.setTitle(result.getString(3));
				report.setReportContent(result.getString(4));
				report.setType(result.getString(5));
				report.setTime(result.getTime(6).toLocalTime());
				report.setDate(result.getDate(7).toLocalDate());
				report.setStatus(result.getString(8));
				report.setInKnowledge(result.getBoolean(9));
				reports.add(0, report);
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
				Report report = new Report();
				User user = new User();
				
				report.setReportid(result.getInt(1));
				user.setUid(result.getString(2));
				report.setAuthor(user);
				report.setTitle(result.getString(3));
				report.setReportContent(result.getString(4));
				report.setType(result.getString(5));
				report.setTime(result.getTime(6).toLocalTime());
				report.setDate(result.getDate(7).toLocalDate());
				report.setStatus(result.getString(8));
				report.setInKnowledge(result.getBoolean(9));
				reports.add(0, report);
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
	
		
	public static List<Report> getKnowledgeReports(){
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
			ps = con.prepareStatement("SELECT * FROM reports WHERE inKnowledge = ?");
			ps.setBoolean(1, true);
			result = ps.executeQuery();
			//result = con.createStatement().executeQuery(query);
			
			while(result.next()){ //step 5
				Report report = new Report();
				User user = new User();
				
				report.setReportid(result.getInt(1));
				user.setUid(result.getString(2));
				report.setAuthor(user);
				report.setTitle(result.getString(3));
				report.setReportContent(result.getString(4));
				report.setType(result.getString(5));
				report.setTime(result.getTime(6).toLocalTime());
				report.setDate(result.getDate(7).toLocalDate());
				report.setStatus(result.getString(8));
				report.setInKnowledge(result.getBoolean(9));
				reports.add(0, report);
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
			ps = con.prepareStatement("INSERT INTO reports VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, reportid);
			ps.setString(2, uid);
			ps.setString(3, title);
			ps.setString(4, reportContent);
			ps.setString(5, type);
			ps.setTime(6, tempTime);
			ps.setDate(7, tempDate);
			ps.setString(8, status);
			ps.setBoolean(9, false);
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
		String varNames[] = {"ReportID", "UserID", "Title", "ReportContent", "Type", "Time", "Date", "Status", "inKnowledge"};
		String varType[] = {"INT", "VARCHAR(80)", "VARCHAR(80)", "VARCHAR(1000)", "VARCHAR(80)", "TIME", "DATE", "VARCHAR(80)", "BOOLEAN"};
		
		Database.createTableString("reports", varNames, varType);
	}
}
