import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class userBean {

	static ArrayList<User> users;
	String username="root";
	String password = "realDeal452";
	Connection con;
	java.sql.Statement stmt;

	public userBean()
	{

	}

	@PostConstruct
	public void createUserList()
	{
		users = new ArrayList<User>();
		try {
			makeDatabaseConnection();
			getAllUserInfo(con);

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



	static void getAllUserInfo(Connection connect) throws Exception
	{
		String query = "select * from userinfo";
		Statement st = connect.createStatement();

		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			String username = rs.getString("username");
			String pwd = rs.getString("password");
			String hierarchy_level = rs.getString("hierarchy_level");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String DOB = rs.getString("DOB");
			String gender = rs.getString("gender");
			String email = rs.getString("email");
			String phoneNumber = rs.getString("phoneNumber");
			String userType = rs.getString("userType");
			String rooms = rs.getString("rooms");

			//Display values
			System.out.println("username: " + username);
			System.out.println("password: " + pwd);
			System.out.println("hierarchy level: " + hierarchy_level);
			System.out.println("firstname: " + firstName);
			System.out.println("lastname: " + lastName);
			System.out.println("DOB: " + DOB);
			System.out.println("gender: " + gender);
			System.out.println("email: " + email);
			System.out.println("phoneNumber: " + phoneNumber);
			System.out.println("userType: " + userType);
			System.out.println("rooms: " + rooms);

			System.out.println();	//Only here for readability of the rooms
			User newUser = new User(firstName,  lastName,  DOB, gender,  email,  phoneNumber, pwd,  userType, rooms);
			users.add(newUser);

		}

		rs.close();



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

	static String getUsername(String firstName, String lastName, Connection con) throws Exception
	{
		String username = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";
		System.out.println(query);

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			username = rs.getString("username");
		}

		rs.close();

		return username;
	}

	static String getPassword(String firstName, String lastName, Connection con) throws Exception
	{
		String password = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			password = rs.getString("password");
		}

		rs.close();

		return password;
	}

	static String getHierarchy(String firstName, String lastName, Connection con) throws Exception
	{
		String hierarchy = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			hierarchy = rs.getString("hierarchy_level");
		}

		rs.close();

		return hierarchy;
	}

	static String getDOB(String firstName, String lastName, Connection con) throws Exception
	{
		String dob = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			dob = rs.getString("DOB");
		}

		rs.close();

		return dob;
	}

	static String getGender(String firstName, String lastName, Connection con) throws Exception
	{
		String gender = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			gender = rs.getString("gender");
		}

		rs.close();

		return gender;
	}

	static String getEmail(String firstName, String lastName, Connection con) throws Exception
	{
		String email = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			email= rs.getString("email");
		}

		rs.close();

		return email;
	}

	static String getPhoneNumber(String firstName, String lastName, Connection con) throws Exception
	{
		String phoneNumber = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			phoneNumber = rs.getString("phoneNumber");
		}

		rs.close();

		return phoneNumber;
	}

	static String getUserType(String firstName, String lastName, Connection con) throws Exception
	{
		String userType = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			userType = rs.getString("userType");
		}

		rs.close();

		return userType;
	}

	static String getRooms(String firstName, String lastName, Connection con) throws Exception
	{
		String rooms = "";
		String query = "select * from userinfo where firstName=\"" + firstName + "\" and lastName=\"" + lastName + "\"";

		Statement st = con.createStatement();
		java.sql.ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			rooms = rs.getString("rooms");
		}

		rs.close();

		return rooms;
	}


	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
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