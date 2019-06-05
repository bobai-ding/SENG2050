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
	public static void createTableString(String tableName, String[] varNames, int[] charVals) {
		String createStatement = "CREATE TABLE " + tableName + "(";
		for (int i=0; i<varNames.length; i++) {
			createStatement += varNames[i] + " VARCHAR(" + charVals[i] + ")";
			
			if(i < varNames.length-1) {
				createStatement += ",";
			}
		}
		createStatement += ")";
		
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
	
	// Execute a database command
	// Use_db for the "USE databaseName" command
	public static void dbExecute(String toExecute) {
		try {
			connect();
			stmt.executeUpdate(toExecute);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
		    try { stmt.close(); } catch (Exception e) { /* ignored */ }
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
	}
	
	// Remove an entry
	public static void deleteEntry(String title, String tableName) {
		try {
			connect();
			ps = conn.prepareStatement("DELETE FROM " + tableName + " WHERE title = ?");
			ps.setString(1, title);
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

	// Delete entire table
	public static void removeTable(String tableName) {
		dbExecute("DROP TABLE " + tableName);
		
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
