import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class rulesBean implements Serializable{

	/*
	 * Make connection to database
	 * Pull information from database
	 * create objects of of database entries
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username = "root";
	String password = "realDeal452";
	private List<rule> rules;
	Connection con;
	java.sql.Statement stmt;


	public rulesBean()
	{
		rules = new ArrayList<rule>();
		try {
			makeDatabaseConnection();
			pullData();

		} catch (Exception e)
		{
			System.out.println("Error connecting to database.  See stacktrace below.");
			e.printStackTrace();
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

	public void createEntryObjects(String deviceName, String action, String time, String date, String notification, String notificationOn, String isOn)
	{
		/*
		 * 
		 *Logic to convert everything to correct type
		 */
		
		deviceEntry rule = new deviceEntry(deviceName, rateStr, statusStr, totalStr, room);
		rules.add(rule);
	}

	public void pullData() throws SQLException
	{
		String query = "select * from waterdevices";
        stmt = con.createStatement();
 
        java.sql.ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next())
        {

        	/*
        	 * WRITE QUERIE!!!
        	 * 
        	 */
            
            createEntryObjects(deviceName, action, time, date, notification, notificationOn, isOn);
            
/*
            //Display values
            System.out.println("device: " + device);
            System.out.println("Gallons Rate: " + gallonsRate);
            System.out.println("Status: " + status);
            System.out.println("Room: " + room);*/
        }
        
        rs.close();
       
        con.close();
//        System.out.println("Finished");

	}

	public void makeDatabaseConnection() throws Exception
	{
		//Load driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Loaded driver");

		//Get connection to database
		con = DriverManager.getConnection("jdbc:mysql://ShelleyTong-HP:3306/smarthome", username, password);
		System.out.println("Connected to MySQL");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<rule> getRules() {
		return rules;
	}

	public void setRules(List<rule> rules) {
		this.rules = rules;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public java.sql.Statement getStmt() {
		return stmt;
	}

	public void setStmt(java.sql.Statement stmt) {
		this.stmt = stmt;
	}




}