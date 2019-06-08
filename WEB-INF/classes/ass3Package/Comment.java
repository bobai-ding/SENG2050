/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Comment Object
*/

package ass3Package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String comment;
    private User author;
    private LocalTime time;
    private LocalDate date;
    private boolean edited = false;
    
    private int reportid;
	private int commentNum;
	
	// Constructor
    Comment(){
    	
    }
    
    // Getters and Setters
    public String getComment(){

        //returns a comment
        return this.comment;
    }
    
	public void setComment(String comment) {
		// Set comment
		this.comment = comment;
	}
	
    public LocalTime getTime() {
    	// Return time
    	return time;
    }
    
	public void setTime(LocalTime time) {
		// Set time
		this.time = time;
	}

    public void setAuthor(User author){

        //set the author of a comment

        this.author = author;
    }

    public void setCommentPos(int commentNumber){

        //basically a unique identifier
        this.commentNum = commentNumber;
    }

    public int getCommentPos(){

        //return the unique ID
        return commentNum;
    }

    public String getAuthor() {

        //return the user name as a string
        return author.getUid();
    }

    public User getAuthorObect(){

        //return the user as an object
        return author;
    }
    
    public LocalDate getDate() {
    	// Get date
		return date;
	}

	public void setDate(LocalDate date) {
		// Set date
		this.date = date;
	}

	public boolean isEdited() {
		// Get true if edited
		return edited;
	}

	public void setEdited(boolean edited) {
		// Set edited
		this.edited = edited;
	}

	public int getReportid() {
		// Get Report id
		return reportid;
	}

	public void setReportid(int reportid) {
		// Set Report id
		this.reportid = reportid;
	}

	public int getCommentNum() {
		// Get comment num
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		// Set comment num
		this.commentNum = commentNum;
	}
	
    @Override
	public String toString() {
		return "Comment [comment=" + comment + ", author=" + author + ", time=" + time + ", date=" + date + ", edited="
				+ edited + ", reportid=" + reportid + ", commentNum=" + commentNum + "]";
	}

	// Database
    // Return all comments as a list
	public static List<Comment> getAllComments(){
		// Temp variables
		String query = "SELECT * FROM comments";
		List<Comment> comments = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		// Try getting comments from database
		try { 
			// Connect to db
			con = Config.getConnection();
			
			// Check table exists
			if(!(Database.checkTableExists("comments"))){
				createCommentTable();
			}
			// Query database for comments
			result = con.createStatement().executeQuery(query);
			while(result.next()){ //step 5
				Comment comment = new Comment();
				User user = new User();
				
				// Retrieve values from database
				comment.setCommentNum(result.getInt(1));
				comment.setReportid(result.getInt(2));
				// TODO check this against other users
				user.setUid(result.getString(3));
				comment.setAuthor(user);
				comment.setComment(result.getString(4));
				comment.setTime(result.getTime(5).toLocalTime());
				comment.setDate(result.getDate(6).toLocalDate());
				comment.setEdited(result.getBoolean(7));
				comments.add(0, comment);
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
		}
		return comments;
		
	}
    // Return all comments as a list
	public static List<Comment> getSpecificComments(int reportid){
		// Temp variables
		String query = "SELECT * FROM comments WHERE ReportID = " + reportid;
		List<Comment> comments = new LinkedList<>();
		Connection con = null;
		ResultSet result = null;
		
		// Try getting comments from database
		try { 
			// Connect to db
			con = Config.getConnection();
			// Check table exists
			if(!(Database.checkTableExists("comments"))){
				createCommentTable();
			}
			// Query database for comments
			result = con.createStatement().executeQuery(query);
			while(result.next()){
				// Temp variables
				Comment comment = new Comment();
				User user = new User();
				
				// Retrieve values from query
				comment.setCommentNum(result.getInt(1));
				comment.setReportid(result.getInt(2));
				// TODO check this against other users
				user.setUid(result.getString(3));
				comment.setAuthor(user);
				comment.setComment(result.getString(4));
				comment.setTime(result.getTime(5).toLocalTime());
				comment.setDate(result.getDate(6).toLocalDate());
				comment.setEdited(result.getBoolean(7));
				comments.add(0, comment);
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
		}
		return comments;
		
	}
	
	// Add a new comment to database
	public static void addComment(int reportid, String comment, String uid, boolean edited) {
		// Set temp values
		Connection con = null;
		PreparedStatement ps = null;
		Time tempTime = Time.valueOf(LocalTime.now());
		Date tempDate = Date.valueOf(LocalDate.now());
		int commentNum = Database.numOfEntrys("comments", reportid);
		
		// Try adding comment to database
		try {
			// Get connection to database
			con = Config.getConnection();
			// Prepare db request
			ps = con.prepareStatement("INSERT INTO comments VALUES (?,?,?,?,?,?,?)");
			// Set request values
			ps.setInt(1, commentNum);
			ps.setInt(2, reportid);
			ps.setString(3, uid);
			ps.setString(4, comment);
			ps.setTime(5, tempTime);
			ps.setDate(6, tempDate);
			ps.setBoolean(7, edited);
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
	public static void createCommentTable() {
		String varNames[] = {"CommentNum", "ReportID", "UserID", "ReportContent", "Time", "Date", "Edited"};
		String varType[] = {"INT","INT", "VARCHAR(80)", "VARCHAR(500)", "TIME", "DATE", "BOOLEAN"};
		
		Database.createTableString("comments", varNames, varType);
	}


}
