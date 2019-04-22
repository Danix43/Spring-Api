package danix43.api;

// TODO moved to the root folder

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import danix43.api.database.Private;
import danix43.api.database.Stb;
import danix43.api.database.Termometre;
import danix43.api.database.TermometreDTO;
import danix43.api.database.repo.PrivateRepository;
import danix43.api.database.repo.StbRepository;
import danix43.api.database.repo.TermometreRepository;

@RestController
public class APIControl {
	
	@Autowired
	private PrivateRepository privateDevicesAccess;
	@Autowired
	private StbRepository transportStbAccess;
	@Autowired
	private TermometreRepository termometruAccess;
	@Autowired
	private ModelMapper modelMapper;
	
	// root
	@GetMapping("/")
	public String root() {
		return "Welcome user";
	}
	
	// house branch
	
	// termometre branch
	
	@GetMapping(path = "/house/termometre", params = "location")	
	public @ResponseBody Iterable<Termometre> getStationByLocation(
			@RequestParam String location) {
		return termometruAccess.findByLocation(location);
	}
	
	@GetMapping(path = "/house/termometre", params = "machineName")
	public @ResponseBody Iterable<Termometre> getStationByMachineName(
			@RequestParam String machineName) {
		return termometruAccess.findByMachinename(machineName);
	}
	
	@GetMapping(path = "/house/termometre", params = "usedSensor")
	public @ResponseBody Iterable<Termometre> getStationByUsedSensor(
			@RequestParam String usedSensor) {
		return termometruAccess.findByUsedsensor(usedSensor);
	}
	
	@GetMapping(path = "/house/termometre", params = "id")
	public @ResponseBody Termometre getStationById(
			@RequestParam int id) {
		return termometruAccess.findById(id);
	}
	
	@GetMapping(path = "/house/termometre/all")
	public @ResponseBody Iterable<Termometre> getAllRegisteredStation() {
		return termometruAccess.findAll();
	}
	
	@PostMapping(path = "/house/termometre/add",
				consumes = "application/json")
	public ResponseEntity<Termometre> addNewStation(
			@RequestBody TermometreDTO termometruToAdd,
			@RequestParam(name = "id", required = true) int id) {
		
		Termometre termometruToSave = modelMapper.map(termometruToAdd, Termometre.class);
		termometruAccess.save(termometruToSave);
		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@PostMapping(path = "/house/termometre/update",
				consumes = "application/json")
	public ResponseEntity<Termometre> updateExistingInformation(
			@RequestBody Termometre termometruToUpdate, 
			@RequestParam(name = "id", required = true) int id) {
		
		Termometre termometruPersistent = termometruAccess.findById(id);
		termometruPersistent.setMachinename(termometruToUpdate.getMachinename());
		termometruPersistent.setMachinetype(termometruToUpdate.getMachinetype());
		termometruPersistent.setUsedsensor(termometruToUpdate.getUsedsensor());
		termometruPersistent.setLocation(termometruToUpdate.getLocation());
		termometruPersistent.setTemperature(termometruToUpdate.getTemperature());
		termometruPersistent.setTemperatureinkelvin(termometruToUpdate.getTemperatureinkelvin());
		termometruPersistent.setHumidity(termometruToUpdate.getHumidity());
		
		termometruAccess.saveAndFlush(termometruPersistent);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	// private database branch
	
	@GetMapping(path = "/private/device/all")
	public @ResponseBody Iterable<Private> getAllTheDevicesRegistered() {
		return privateDevicesAccess.findAll();
	}
	
	@GetMapping(path = "/private/device", params = "name")
	public @ResponseBody Iterable<Private> getDeviceByName(
			@RequestParam() String name) {
		return privateDevicesAccess.findByName(name);
	}
	
	@GetMapping(path = "/private/device", params = "macAddress")
	public Iterable<Private> getDeviceByMacAddress(
			@RequestParam() String macAddress) {
		return privateDevicesAccess.findByMacaddress(macAddress);
	}
	
	@GetMapping(path = "/private/device", params = "lastKnownDate")
	public Iterable<Private> getDeviceByLastDateKnown(
			@RequestParam() String date) {
		return privateDevicesAccess.findByLastdateknown(date);
	}
	
	// stb branch
	
	@GetMapping(path = "/transport/stb/all")
	public @ResponseBody Iterable<Stb> getAllStbEntries() {
		return transportStbAccess.findAll(); 
	}
	
	@GetMapping(path = "/transport/stb", params = "statia")
	public @ResponseBody List<Stb> getStatie(
			@RequestParam() String statia) {
		return transportStbAccess.findByStatia(statia);
	}
	
	@GetMapping(path = "/transport/stb", params = "linia")
	public @ResponseBody List<Stb> getLinie(
			@RequestParam() String linia) {
		return transportStbAccess.findByLinia(linia);
	}
	
}
