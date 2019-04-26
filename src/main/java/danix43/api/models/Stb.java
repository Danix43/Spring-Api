package danix43.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "stb")
public class Stb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int nid;
	private String lon;
	private String lat;
	private String type;
	private String statia;
	private String linia;
	
	public int getId() {
		return id;
	}
	public int getNid() {
		return nid;
	}
	public String getLon() {
		return lon;
	}
	public String getLat() {
		return lat;
	}
	public String getType() {
		return type;
	}
	public String getStatia() {
		return statia;
	}
	public String getLinia() {
		return linia;
	}

}
