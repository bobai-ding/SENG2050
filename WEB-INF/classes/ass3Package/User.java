/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: User Object
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
    
    // Get first name
	public String getFirstName() {
		return firstName;
	}

	// Set first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Get last name
	public String getLastName() {
		return lastName;
	}

	// Set last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Get email
	public String getEmail() {
		return email;
	}

	// Set email
	public void setEmail(String email) {
		this.email = email;
	}

	// Get phone number
	public int getPhoneNum() {
		return phoneNum;
	}

	// Set phone number
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
			// Query database
			ps = con.prepareStatement("SELECT * FROM tomcat_users WHERE user_name = ?");
			ps.setString(1, uid);
			result = ps.executeQuery();
			
			while(result.next()){
				// Set values
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
