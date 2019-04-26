package danix43.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(schema = "termometre")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicUpdate(value = true)
public class Termometre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String machinename;
	
	private String machinetype;
	
	private String usedsensor;
	
	private String location;
	
	private float temperature;
	
	private float temperatureinkelvin;
	
	private float humidity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
