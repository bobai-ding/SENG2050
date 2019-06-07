package ass3Package;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Database {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement ps = null; 
	private static ResultSet rs = null;

	// Check if a table exists
	public static boolean checkTableExists(String tableName) {
		try {
			connect();
			DatabaseMetaData meta = conn.getMetaData();
			// check if "employee" table is there
			rs = meta.getTables(conn.getCatalog(), null, tableName, new String[] {"TABLE"});
			if(rs.next()) {
				System.out.println("LOG: Table exists");
				return true;
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		System.err.println("Table does not exist");
		return false;
	}
	
	// Create a new table
	public static void createTableString(String tableName, String[] varNames, String[] varType) {
		String createStatement = "CREATE TABLE " + tableName + "(";
		for (int i=0; i<varNames.length; i++) {
			createStatement += varNames[i] + " " + varType[i];
			
			if(i < varNames.length-1) {
				createStatement += ",";
			}
		}
		createStatement += ")";
		
		System.out.println("LOG: creating table: \n" + createStatement);
		try {
			// Connect and query
			connect();
			stmt.executeUpdate(createStatement);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// connection and statement setting
	public static Statement connect() throws Exception {
		conn = Config.getConnection();
		return stmt = conn.createStatement();
	}
	
	// Remove an entry
	// eg. to delete report: 1
	// Set id = 1, colName = ReportID, tableName = reports
	public static void deleteEntry(int id, String colName, String tableName) {
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE " + colName + " = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Remove an entry
	public static void deleteEntry(String entry, String colName, String tableName) {
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE " + colName + " = ?");
			ps.setString(1, entry);
			ps.executeUpdate();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}

	// View a specific report using userid and title
	public static Report viewSpecificReport(String uid, String title) {
		Report report = null;
		try {
			// connect and query
			connect();
			ps = conn.prepareStatement("SELECT * FROM reports WHERE UserID = ? AND Title = ?");
			ps.setString(1, uid);
			ps.setString(2, title);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				report = new Report();
				
				report.setReportid(rs.getInt(1));
				report.setAuthor(User.getSpecificUser(rs.getString(2)));
				report.setTitle(rs.getString(3));
				report.setReportContent(rs.getString(4));
				report.setType(rs.getString(5));
				report.setTime(rs.getTime(6).toLocalTime());
				report.setDate(rs.getDate(7).toLocalDate());
				report.setStatus(rs.getString(8));
				report.setInKnowledge(rs.getBoolean(9));
				
				if (rs.getTime(10) != null) {
					report.setTimeResolved(rs.getTime(10).toLocalTime());	
				} else {
					report.setTimeResolved(null);
				}
				
				if (rs.getDate(11) != null) {
					report.setDateResolved(rs.getDate(11).toLocalDate());
				} else {
					report.setDateResolved(null);
				}
				
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return report;
	}
	// Return a specific report using 
	public static Report viewSpecificReport(int reportid) {
		Report report = null;
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("SELECT * FROM reports WHERE ReportID = ?");
			ps.setInt(1, reportid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				report = new Report();
				
				report.setReportid(rs.getInt(1));
				report.setAuthor(User.getSpecificUser(rs.getString(2)));
				report.setTitle(rs.getString(3));
				report.setReportContent(rs.getString(4));
				report.setType(rs.getString(5));
				report.setTime(rs.getTime(6).toLocalTime());
				report.setDate(rs.getDate(7).toLocalDate());
				report.setStatus(rs.getString(8));
				report.setInKnowledge(rs.getBoolean(9));
				
				if (rs.getTime(10) != null) {
					report.setTimeResolved(rs.getTime(10).toLocalTime());	
				} else {
					report.setTimeResolved(null);
				}
				
				if (rs.getDate(11) != null) {
					report.setDateResolved(rs.getDate(11).toLocalDate());
				} else {
					report.setDateResolved(null);
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return report;
	}
	
	public static Report viewUserReports(String userID) {
		Report report = null;
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("SELECT * FROM reports WHERE UserID = ?");
			ps.setString(1, userID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				report = new Report();
				
				report.setReportid(rs.getInt(1));
				report.setAuthor(User.getSpecificUser(rs.getString(2)));
				report.setTitle(rs.getString(3));
				report.setReportContent(rs.getString(4));
				report.setType(rs.getString(5));
				report.setTime(rs.getTime(6).toLocalTime());
				report.setDate(rs.getDate(7).toLocalDate());
				report.setStatus(rs.getString(8));
				report.setInKnowledge(rs.getBoolean(9));
				
				if (rs.getTime(10) != null) {
					report.setTimeResolved(rs.getTime(10).toLocalTime());	
				} else {
					report.setTimeResolved(null);
				}
				
				if (rs.getDate(11) != null) {
					report.setDateResolved(rs.getDate(11).toLocalDate());
				} else {
					report.setDateResolved(null);
				}
				
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return report;
	}


	// Delete entire table
	public static void removeTable(String tableName) {
		try {
			connect();
			stmt.executeUpdate("DROP TABLE " + tableName);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Num of entries matching reportid
	public static int numOfEntrys(String tableName, int reportid) {
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("SELECT COUNT(*) FROM " + tableName + " WHERE ReportID = ?");
			ps.setInt(1, reportid);
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return 0;
	}
	
	// Num of database entrys
	public static int numOfEntrys(String tableName) {
		try {
			// Connect and query
			connect();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return 0;
	}
	
	// Find max int from tables
	public static int max(String tableName, String colName) {
		try {
			// Connect and query database
			connect();
			rs = stmt.executeQuery("SELECT MAX(" + colName + ") FROM " + tableName);
			
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return 0;
	}
	
	// Update 
	public static void update(String tableName, String colName, Object setValue) {
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("UPDATE " + tableName + " SET " + colName + " = ?");
			ps.setObject(1, setValue);
			ps.executeUpdate();
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Return a specific report using 
	public static void updateWhere(String tableName, String colName, Object setValue, String whereType, Object whereValue) {
		try {
			// Connect and query
			connect();
			ps = conn.prepareStatement("UPDATE " + tableName + " SET " + colName + " = ? WHERE " + whereType + " = ?");
			ps.setObject(1, setValue);
			ps.setObject(2, whereValue);
			ps.executeUpdate();
			
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Just a try catch template
	public static void tryCatchTemplate() {
		try {
			//Try something
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
}
