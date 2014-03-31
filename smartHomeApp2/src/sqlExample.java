import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlExample
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{	

		String dbName = "smarthome";
		String username = "root";
		String password = "realDeal452";
		Statement stmt = null;
		
        try 
        {
        	//Load driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Loaded driver");
            
            //Get connection to database
            Connection con = DriverManager.getConnection("jdbc:mysql://ShelleyTong-HP:3306/smarthome", username, password);
            System.out.println("Connected to MySQL");
            
            System.out.println("Creating arbitrary table");
            //Create a table with the database.
            String createString =
                    "create table sharlina(value varchar(100), primary key(value))";
             
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
           
            con.close();
            System.out.println("Finished");
     } catch (Exception ex) 
     {
            ex.printStackTrace();
     } 
     finally 
     {
          if (stmt != null) 
          { 
            try 
            {
				stmt.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			} 
          }
     }

	}

}
