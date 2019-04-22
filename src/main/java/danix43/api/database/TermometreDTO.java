package danix43.api.database;

public class TermometreDTO {

	private String machinename;
	
	private String machinetype;
	
	private String usedsensor;
	
	private String location;
	
	private float temperature;
	
	private float temperatureinkelvin;
	
	private float humidity;


	public String getMachinename() {
		return machinename;
	}

	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}

	public String getMachinetype() {
		return machinetype;
	}

	public void setMachinetype(String machinetype) {
		this.machinetype = machinetype;
	}

	public String getUsedsensor() {
		return usedsensor;
	}

	public void setUsedsensor(String usedsensor) {
		this.usedsensor = usedsensor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getTemperatureinkelvin() {
		return temperatureinkelvin;
	}

	public void setTemperatureinkelvin(float temperatureinkelvin) {
		this.temperatureinkelvin = temperatureinkelvin;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

}
