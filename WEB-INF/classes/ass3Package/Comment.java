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

    Comment(){
    	
    }

    Comment(String comment, User author){

        //comment constructor w/ variables

        //set the time on creation
        time = LocalTime.now();
        date = LocalDate.now();

        //set values
        this.comment = comment;
        this.author = author;
    }

    public void setComment(String comment, User author) {
        //set the variables of the object
        //updates the time / date as what they were on creation
        time = LocalTime.now();
        date = LocalDate.now();
        this.author = author;
        this.comment = comment;
    }

    public String getComment(){

        //returns a comment
        return this.comment;
    }
    
	public void setComment(String comment) {
		this.comment = comment;
	}
	
    public void editComment(String newComment){

        //edit the comment with new text


        this.edited = true;
        this.comment = newComment;
    }

    public Date getTimeParsed(){

        Date date = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        DateFormat d2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        DateFormat outputformat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        try{
            date = (Date) d2.parse(time.toString() + date.toString());
            return date;
        } catch (ParseException e){
            e.printStackTrace();
        }



        return null;
        //format the date + time to make one string
        //need this for sorting the linked list
    }
    
    public LocalTime getTime() {
    	return time;
    }
    
	public void setTime(LocalTime time) {
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
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}

	public int getReportid() {
		return reportid;
	}

	public void setReportid(int reportid) {
		this.reportid = reportid;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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
				System.out.println("TESTING: userid: " + user.getUid());
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
		String varType[] = {"INT","INT", "VARCHAR(80)", "VARCHAR(200)", "TIME", "DATE", "BOOLEAN"};
		
		Database.createTableString("comments", varNames, varType);
	}


}
