package ass3Package;

import java.util.LinkedList;
import java.util.List;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	private User author;
    private String reportContent;
    
    private java.time.LocalTime time;
    private java.time.LocalDate date;

    private LinkedList<Comment> comments = new LinkedList<>();

    Report(){

    }

    public void newReport(String report, User author){

        //create a new report with a report string and user object

        time = java.time.LocalTime.now();
        date = java.time.LocalDate.now();
        this.reportContent = report;
        this.author = author;

        System.out.println(time);
        System.out.println(date);

        System.out.println(report);
        System.out.println(author.getUid());
    }

    public void editReport(String report){

        //need to format the time to remove the ms

        //probs need to format the date to aus standards

        time = java.time.LocalTime.now();
        date = java.time.LocalDate.now();
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
    
	public java.time.LocalTime getTime() {
		return time;
	}

	public void setTime(java.time.LocalTime time) {
		this.time = time;
	}

	public java.time.LocalDate getDate() {
		return date;
	}

	public void setDate(java.time.LocalDate date) {
		this.date = date;
	}

	public String getReportContent() {
		return reportContent;
	}

    public void setReportContent(String content) {
    	this.reportContent = content;
    }
    
    
	//have an array list of the comment shiz

    
    // Database
    // Return all reports as a list
	public static List<Report> getAllReports(){		
		String query = "SELECT * FROM reports";
		List<Report> reports = new LinkedList<>();
		Connection con = null;
		try { 
			con = Config.getConnection();
			Statement statement = con.createStatement();
			
			// Check table exists
			if(!(Database.checkTableExists("reports"))){
				createReportTable();
			}
			
			ResultSet result = statement.executeQuery(query); //step 3 and 4
			while(result.next()){ //step 5
				Report report = new Report();
				User user = new User();
				
				user.setUid(result.getString(1));
				report.setAuthor(user);
				//report.setReportContent(result.getString(2));
				//report.setTime(result.getTime(3));
				reports.add(report);
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
		}
		return reports;
		
	}
	
	// Add a new report to database
	public static void addReport(String uid) {
		Connection con = null;
		try {
			con = Config.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO reports VALUES (?)");
			ps.setString(1, uid);
			//ps.setInt(2, date);
			//ps.setString(3, url);
			ps.executeUpdate();
		} catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}	
	
	// Create table in database for reports
	public static void createReportTable() {
		String varNames[] = {"UserID"};
		int charVals[] = {80, 4, 256};
		
		Database.createTableString("reports", varNames, charVals);
	}
}
