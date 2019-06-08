/*
	Author: William Paterson, c3280751
	Author: Simeon Pento, c3282938
	Author: Lachlan McRae, c3283344
	
	Last Modified: 9/6/19
	Description: Database Config class
*/

package ass3Package;

import javax.sql.*;
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Config {
	public static Connection getConnection() throws NamingException, SQLException {
		try {
			// Find datasource in web.xml
			DataSource datasource = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/SENG2050");
			// Initiate a commection	
			Connection connection = datasource.getConnection();
			return connection;
		}
		catch (NamingException ne) {
			System.err.println(ne.getMessage());
			System.err.println(ne.getStackTrace());
			throw ne;
		}
		catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getStackTrace());
			throw sqle;
		}
	}
}
