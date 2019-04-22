package danix43.api.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "private")
public class Private {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String macaddress;
	private String lastdateknown;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMacAddress() {
		return macaddress;
	}
	public String getLastDateKnown() {
		return lastdateknown;
	}
}
