import java.io.Serializable;

public class deviceEntry implements Serializable{

	/*
	 * at some point, you have to make deviceName an actual device object
	 * so create a device class with several device properties
	 * same goes for room.  make a room object with several room properties
	 */

	String deviceName;
	String rate;
	String status;
	String total;
	String room;

	public deviceEntry(String deviceName, String rate, String status, String total, String room)
	{
		this.deviceName = deviceName;
		this.rate = rate;
		this.status = status;
		this.total = total;
		this.room = room;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}