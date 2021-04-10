package databaseSQL;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLite {
		public static Connection dbConnector() 
		{
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment2", "root", "Moocow66");
				return conn;
			}
			catch(Exception e) 
			{
				JOptionPane.showMessageDialog(null,e);
				return null;
			}
			
		}
}
