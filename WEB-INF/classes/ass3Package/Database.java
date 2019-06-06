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
	
	// Check if a specific database exists
	public static boolean checkDBExists(String dbName) {
		try{
			connect();
			
			if(conn != null) {
				rs = conn.getMetaData().getCatalogs();
				while(rs.next()){
					String catalogs = rs.getString(1);
					
					if(dbName.equals(catalogs)){
						System.out.println("LOG: Database " + dbName + " exists");
						return true;
					}
				}
			}
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		} 
		finally {
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		return false;
	}
	
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
		
		System.out.println("TESTING: creating table: \n" + createStatement);
		try {
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

	// View a specific report
	public static Report viewSpecificReport(String uid, String title) {
		Report report = null;
		User user = null;
		try {	
			connect();
			ps = conn.prepareStatement("SELECT * FROM reports WHERE UserID = ? AND Title = ?");
			ps.setString(1, uid);
			ps.setString(2, title);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				report = new Report();
				user = new User();
				
				report.setReportid(rs.getInt(1));
				user.setUid(rs.getString(2));
				report.setAuthor(user);
				report.setTitle(rs.getString(3));
				report.setReportContent(rs.getString(4));
				report.setType(rs.getString(5));
				report.setTime(rs.getTime(6).toLocalTime());
				report.setDate(rs.getDate(7).toLocalDate());
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

	public static Report viewSpecificReport(int reportid) {
		Report report = null;
		User user = null;
		try {	
			connect();
			ps = conn.prepareStatement("SELECT * FROM reports WHERE ReportID = ?");
			ps.setInt(1, reportid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				report = new Report();
				user = new User();
				
				report.setReportid(rs.getInt(1));
				user.setUid(rs.getString(2));
				report.setAuthor(user);
				report.setTitle(rs.getString(3));
				report.setReportContent(rs.getString(4));
				report.setType(rs.getString(5));
				report.setTime(rs.getTime(6).toLocalTime());
				report.setDate(rs.getDate(7).toLocalDate());
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
	
	public static int numOfEntrys(String tableName, int reportid) {
		try {
			connect();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName + "WHERE ReportID = " + reportid);
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
	
	public static int numOfEntrys(String tableName) {
		try {
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
