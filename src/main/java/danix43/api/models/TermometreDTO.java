package danix43.api.models;

public class TermometreDTO {

	private String machinename;
	
	private String machinetype;
	
	private String usedsensor;
	
	private String location;
	
	private float temperature;
	
	private float temperatureinkelvin;
	
	private float humidity;
	
	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}

	public void setMachinetype(String machinetype) {
		this.machinetype = machinetype;
	}

	public void setUsedsensor(String usedsensor) {
		this.usedsensor = usedsensor;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setTemperatureinkelvin(float temperatureinkelvin) {
		this.temperatureinkelvin = temperatureinkelvin;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public String getMachinename() {
		return machinename;
	}

	public String getMachinetype() {
		return machinetype;
	}

	public String getUsedsensor() {
		return usedsensor;
	}

	public String getLocation() {
		return location;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getTemperatureinkelvin() {
		return temperatureinkelvin;
	}

	public float getHumidity() {
		return humidity;
	}

}
