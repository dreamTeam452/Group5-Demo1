
public class rule {

	String deviceName;
	String action;
	String time;
	String date;
	String notification;
	String notificationOn;
	String isOn;

	public rule(String deviceName, String action, String time, String date, String notification, String notificationOn, String isOn)
	{
		this.deviceName = deviceName;
		this.action = action;
		this.time = time;
		this.date = date;
		this.notification = notification;
		this.isOn = isOn;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotificationOn() {
		return notificationOn;
	}

	public void setNotificationOn(String notificationOn) {
		this.notificationOn = notificationOn;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}
}
