import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class deviceBean {

	String username;
	String password;
	ArrayList<device> devices = new ArrayList<device>();
	
	public deviceBean()
	{
		
	}
	
	public void makeDatabaseConnection()
	{
		try {
			//Load driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Loaded driver");

			//Get connection to database
			Connection con = DriverManager.getConnection("jdbc:mysql://ShelleyTong-HP:3306/smarthome", username, password);
			System.out.println("Connected to MySQL");
		} catch (Exception e)
		{
			System.out.println("Error connecting to database.  See stacktrace below.");
			e.printStackTrace();
		}
	}
}

class device
{

	String deviceName;
	String type;
	int rate;
	boolean status;
	int total;
	String room;
	
	public device()
	{
		
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	
	
	
	/*
	 * 
	 * 

Device
Gallons rate
Status
Total Gallons
Room


	 */
	
	
	
}
