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

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String firstName;
	private String lastName;
	private String email;
	private int phoneNum;

    User(){
        //base constructor
    }
    
    public String getUid() {
        //return the uid
        return uid;
    }

    public void setUid(String uid) {
        //set the uid
        this.uid = uid;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNum=" + phoneNum + "]";
	}
	
	// Return a specific user using uid
	public static User getSpecificUser(String uid){
		// Temp variables
		User user = new User();
		Connection con = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		// Try getting user from database
		try { 
			// Connect to db
			con = Config.getConnection();
			ps = con.prepareStatement("SELECT * FROM tomcat_users WHERE user_name = ?");
			ps.setString(1, uid);
			result = ps.executeQuery();
			while(result.next()){
				user.setUid(result.getString(1));
				user.setFirstName(result.getString(3));
				user.setLastName(result.getString(4));
				user.setEmail(result.getString(5));
				user.setPhoneNum(result.getInt(6));
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		finally {
			try { con.close(); } catch (Exception e) { /* ignored */ }
			try { result.close(); } catch (Exception e) { /* ignored */ }
		}
		return user;
	}
}
