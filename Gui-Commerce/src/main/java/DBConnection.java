import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	// If app is going to be compiled for production on Heroku, set this to true;
	
	// If app is going to be compiled for testing locally, set this to false;
	
	boolean production = false;
	
	public Connection setConnection() throws SQLException, ClassNotFoundException {
		
		String hostname;
		
		String port;
		
		String databasename;
		
		String db_username;
		
		String db_password;
		
		if (production) {
			
			// For heroku postgres:
			
			hostname = "herokuherokuheroku";
			
			port = "5432";
			
			databasename = "herokuherokuheroku";
			
			db_username = "herokuherokuheroku";
			
			db_password = "herokuherokuheroku";
			
		} else {
			
			// For local postgreSQL:
			
			hostname = "localhost";
			
			port = "5432";
			
			databasename = "localhost";
			
			db_username = "localhost";
			
			db_password = "localhost";
		}

		Connection conn = null;
		
        Class.forName("org.postgresql.Driver");
        
        conn = DriverManager
           .getConnection("jdbc:postgresql://"+hostname+":"+port+"/"+databasename+"",
        		  db_username, db_password);
     
        return conn;
	}
}