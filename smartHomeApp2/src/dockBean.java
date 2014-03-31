

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class dockBean {
	private boolean home = false;
	private boolean users = false;
	private boolean devices = false;
	private boolean rooms = false;
	private boolean logs = false;
	private boolean rules = false;
	private boolean reports = false;
	private boolean createUser = false;

	private boolean displayUser = false;
	public boolean isHome() {
		return home;
	}

	public void setHome(boolean temp) {
		this.home = temp;
		users = false;
		devices = false;
		rooms = false;
		logs = false;
		rules = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isUsers() {
		return users;
	}

	public void setUsers(boolean users) {
		this.users = users;
		home = false;
		devices = false;
		rooms = false;
		logs = false;
		rules = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isDevices() {
		return devices;
	}

	public void setDevices(boolean devices) {
		this.devices = devices;
		home = false;
		users = false;
		rooms = false;
		logs = false;
		rules = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isRooms() {
		return rooms;
	}

	public void setRooms(boolean rooms) {
		this.rooms = rooms;
		home = false;
		users = false;
		devices = false;
		logs = false;
		rules = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isLogs() {
		return logs;
	}

	public void setLogs(boolean logs) {
		this.logs = logs;
		home = false;
		users = false;
		devices = false;
		rooms = false;
		rules = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isRules() {
		return rules;
	}

	public void setRules(boolean rules) {
		this.rules = rules;
		home = false;
		users = false;
		devices = false;
		rooms = false;
		logs = false;
		reports = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isReports() {
		return reports;
	}

	public void setReports(boolean reports) {
		this.reports = reports;
		home = false;
		users = false;
		devices = false;
		rooms = false;
		logs = false;
		rules = false;
		displayUser = false;
		createUser = false;
	}

	public boolean isDisplayUser() {
		return displayUser;
	}

	public void setDisplayUser(boolean displayUser) {
		this.displayUser = displayUser;
		home = false;
		users = false;
		devices = false;
		rooms = false;
		logs = false;
		rules = false;
		reports = false;
		createUser = false;
	}

	public boolean isCreateUser() {
		return createUser;
	}

	public void setCreateUser(boolean createUser) {
		this.createUser = createUser;
		home = false;
		users = false;
		devices = false;
		rooms = false;
		logs = false;
		rules = false;
		reports = false;
		displayUser = false;
	}
	
	
	
	

	

}
