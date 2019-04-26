package danix43.api;

// TODO moved to the root folder

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import danix43.api.models.Private;
import danix43.api.models.Stb;
import danix43.api.models.Termometre;
import danix43.api.models.database.PrivateRepository;
import danix43.api.models.database.StbRepository;
import danix43.api.models.database.TermometreRepository;
import danix43.api.models.TermometreDTO;

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

	@GetMapping(path = "/house/termometre/locatie/{location}")
	public @ResponseBody ResponseEntity<Termometre> getStationByLocation(
			@PathVariable String location) {
		if (termometruAccess.findByLocation(location) != null) {
			return ResponseEntity.ok(termometruAccess.findByLocation(location));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/house/termometre/machinename/{machinename}")
	public @ResponseBody ResponseEntity<Termometre> getStationByMachineName(
			@PathVariable String machineName) {
		if (termometruAccess.findByMachinename(machineName) != null) {
			return ResponseEntity.ok(termometruAccess.findByMachinename(machineName));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/house/termometre/sensorUsed/{usedSensor}")
	public @ResponseBody ResponseEntity<Termometre> getStationByUsedSensor(
			@PathVariable String usedSensor) {
		if (termometruAccess.findByUsedsensor(usedSensor) != null) {
			return ResponseEntity.ok(termometruAccess.findByUsedsensor(usedSensor));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/house/termometre/id/{id}")
	public @ResponseBody ResponseEntity<Termometre> getStationById(
			@PathVariable int id) {
		if (termometruAccess.findById(id) != null) {
			return ResponseEntity.ok(termometruAccess.findById(id)); 
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/house/termometre/all")
	public @ResponseBody ResponseEntity<List<Termometre>> getAllRegisteredStation() {
		if (!termometruAccess.findAll().isEmpty()) {
			return ResponseEntity.ok(termometruAccess.findAll());
		} else {
			throw new EntityNotFoundException();
		}
	}

	@PostMapping(path = "/house/termometre/add", consumes = "application/json")
	public ResponseEntity<Termometre> addNewStation(
			@RequestBody TermometreDTO termometruToAdd,
			@RequestParam(name = "id", required = true) int id) {
		Termometre termometruToSave = modelMapper.map(termometruToAdd, Termometre.class);
		termometruAccess.save(termometruToSave);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping(path = "/house/termometre/update", consumes = "application/json")
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

		termometruAccess.save(termometruPersistent);
		return ResponseEntity.ok(termometruPersistent);
	}

	// private database branch

	@GetMapping(path = "/private/device/all")
	public @ResponseBody ResponseEntity<List<Private>> getAllTheDevicesRegistered() {
		if (!privateDevicesAccess.findAll().isEmpty()) {
			return ResponseEntity.ok(privateDevicesAccess.findAll());
		} else {
			throw new EntityNotFoundException();
		}
		
	}

	@GetMapping(path = "/private/device/devicename/{deviceName}")
	public @ResponseBody ResponseEntity<Iterable<Private>> getDeviceByName(
			@PathVariable String deviceName) {
		if (privateDevicesAccess.findByName(deviceName) != null) {
			return ResponseEntity.ok(privateDevicesAccess.findByName(deviceName));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/private/device/macaddress/{macAddress}")
	public ResponseEntity<Iterable<Private>> getDeviceByMacAddress(
			@RequestParam() String macAddress) {
		if (privateDevicesAccess.findByMacaddress(macAddress) != null) {
			return ResponseEntity.ok(privateDevicesAccess.findByMacaddress(macAddress));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/private/device/lastdate/{lastDateKnown}")
	public ResponseEntity<Iterable<Private>> getDeviceByLastDateKnown(
			@PathVariable String lastDateKnown) {
		if (privateDevicesAccess.findByLastdateknown(lastDateKnown) != null) {
			return ResponseEntity.ok(privateDevicesAccess.findByLastdateknown(lastDateKnown));
		} else {
			throw new EntityNotFoundException();
		}
	}

	// stb branch

	@GetMapping(path = "/transport/stb/all")
	public @ResponseBody ResponseEntity<List<Stb>> getAllStbEntries() {
		if (!transportStbAccess.findAll().isEmpty()) {
			return ResponseEntity.ok(transportStbAccess.findAll());
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/transport/stb/statia/{statia}")
	public @ResponseBody ResponseEntity<List<Stb>> getStatie(
			@PathVariable String statia) {
		if (transportStbAccess.findByStatia(statia) != null) {
			return ResponseEntity.ok(transportStbAccess.findByStatia(statia));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/transport/stb/linia.{linia}")
	public @ResponseBody ResponseEntity<List<Stb>> getLinie(
			@PathVariable String linia) {
		if (transportStbAccess.findByLinia(linia) != null) {
			return ResponseEntity.ok(transportStbAccess.findByLinia(linia));
		} else {
			throw new EntityNotFoundException();
		}
	}
}
