import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@ManagedBean
@SessionScoped
public class createUser implements Serializable {

	private static final long serialVersionUID = 1L;
	String user = "root";
	String password = "realDeal452";
	ArrayList<String> selectedRooms = new ArrayList<String>();
	Connection con;
	java.sql.Statement stmt;
	
	String firstName = null;
	String lastName = null;
	String DOB = null;			//date of birth
	String gender = null;
	String email = null;
	String phoneNumber = null;
	String pwd = null;
	String username = null;
	String userType = null;
	String rooms = null;


	public createUser()
	{
	}

	public void constructor()
	{
		//logic to put user into database
		try {
			makeDatabaseConnection();
			addNewUser(username, pwd, userType, firstName, lastName, DOB, gender, email, phoneNumber, userType, selectedRooms, con);

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
	public void makeDatabaseConnection() throws Exception
	{
		//Load driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Loaded driver");

		//Get connection to database
		con = DriverManager.getConnection("jdbc:mysql://ShelleyTong-HP:3306/smarthome", user, password);
		System.out.println("Connected to MySQL");
	}

	private void addNewUser(String username, String password, String hierarchy_level, String firstName, String lastName, String DOB, String gender, String email, String phoneNumber, String userType, ArrayList<String> selectedRooms, Connection con) throws Exception
	{
		String rooms = convertToDatabaseString(selectedRooms);
		
		Statement st = con.createStatement();
		String query = "INSERT INTO userinfo(username, password, hierarchy_level, firstName, lastName, DOB, gender, email, phoneNumber, userType, rooms) " +
                "VALUES ("+ "\"" + username + "\"" + ", " + "\"" + password + "\"" + ", " + "\"" + hierarchy_level + "\"" + ", " + "\"" + firstName + "\"" + ", "+ "\"" +  lastName + "\""+ ", " + "\""+ DOB + "\""+ ", " + "\"" + gender + "\"" + ", "  + "\"" + email + "\"" + ", " + "\"" + phoneNumber + "\"" + ", " + "\"" + userType + "\"" + ", "+ "\""  + rooms + "\"" +")";
		
		System.out.println(query);
		st.executeUpdate(query);
	}
	
	private String convertToDatabaseString(ArrayList<String> allRooms)
	{
		if (allRooms.size() == 0)
			return "";
		
		String rooms = "";
		for (int i = 0; i < allRooms.size(); i++)
		{
			rooms= rooms + "|" + allRooms.get(i);
		}
		
		return rooms.substring(1);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getSelectedRooms() {
		return selectedRooms;
	}

	public void setSelectedRooms(ArrayList<String> selectedRooms) {
		this.selectedRooms = selectedRooms;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
