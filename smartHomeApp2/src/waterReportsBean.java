import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@RequestScoped
public class waterReportsBean implements Serializable{

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
	List<deviceEntry> entries = new ArrayList<deviceEntry>();
	List<deviceEntry> temp = new ArrayList<deviceEntry>();
	Connection con;
	java.sql.Statement stmt;

	@PostConstruct
	public void constructor(){
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

	public waterReportsBean()
	{

	}

	public void createEntryObjects(String deviceName, int rate, String status, int total, String room)
	{
		String rateStr = Integer.toString(rate);
		String totalStr = Integer.toString(total);


		deviceEntry entry = new deviceEntry(deviceName, rateStr, status, totalStr, room);
		entries.add(entry);
	}

	public void pullData() throws SQLException
	{
		String query = "select * from waterdevices";
		stmt = con.createStatement();

		java.sql.ResultSet rs = stmt.executeQuery(query);

		while(rs.next())
		{
			String device = rs.getString("Device");
			int gallonsRate = (int) rs.getDouble("gallonsrate");
			String status = rs.getString("status");
			int totalGallons = (int) rs.getDouble("totalGallons");
			String room = rs.getString("room");

			createEntryObjects(device, gallonsRate, status, totalGallons, room);

			
            //Display values
            System.out.println("device: " + device);
            System.out.println("Gallons Rate: " + gallonsRate);
            System.out.println("Status: " + status);
            System.out.println("Room: " + room);
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

	public List<deviceEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<deviceEntry> entries) {
		this.entries = entries;
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

	public List<deviceEntry> getTemp() {
		return temp;
	}

	public void setTemp(List<deviceEntry> temp) {
		this.temp = temp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}