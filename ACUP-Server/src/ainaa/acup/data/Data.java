package ainaa.acup.data;

public class Data {
	
	private static Data data;
	
	private String pcName;
	private String platform;
	private String username;
	private String pin;
	private Integer checkPort;
	private Integer accessPort;
	
	public static Data getInstance()
	{
		if(null == data)
		{
			data = new Data();
		}
		return data;
	}
	
	

	public Integer getCheckPort() {
		return checkPort;
	}



	public void setCheckPort(Integer checkPort) {
		this.checkPort = checkPort;
	}



	public Integer getAccessPort() {
		return accessPort;
	}



	public void setAccessPort(Integer accessPort) {
		this.accessPort = accessPort;
	}



	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
}
