package danix43.api;

import java.net.URI;
import java.util.Collections;
import java.util.List;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import danix43.api.models.Private;
import danix43.api.models.Stb;
import danix43.api.models.Termometre;
import danix43.api.models.TermometreDTO;
import danix43.api.models.databases.PrivateRepository;
import danix43.api.models.databases.RoleRepository;
import danix43.api.models.databases.StbRepository;
import danix43.api.models.databases.TermometreRepository;
import danix43.api.models.databases.UserRepository;
import danix43.api.modules.authentication.ApiResponse;
import danix43.api.modules.authentication.JwtTokenProvider;
import danix43.api.modules.authentication.Role;
import danix43.api.modules.authentication.RoleName;
import danix43.api.modules.authentication.User;
import danix43.api.modules.authentication.requests.JwtAuthenticationResponse;
import danix43.api.modules.authentication.requests.LoginRequest;
import danix43.api.modules.authentication.requests.SignUpRequest;
import danix43.api.modules.exceptionhandling.exceptions.AppException;

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
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired 
	private UserRepository userAccess;
	@Autowired
	private RoleRepository roleAccess;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	// root
	@GetMapping("/")
	public String root() {
		return "Welcome user";
	}
	
	// authentication branch
	@PostMapping(path = "auth/login/")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
														loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	
	@PostMapping(path = "auth/signup/")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userAccess.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username already used!"), HttpStatus.BAD_REQUEST);
		}
		if (userAccess.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email Adddress already used!"), HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleAccess.findByName(RoleName.USER)
					.orElseThrow(() -> new AppException("User Role not set!"));
		user.setRoles(Collections.singleton(userRole));
		User result = userAccess.save(user);
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentContextPath().path("/api/users/{username}/")
						.buildAndExpand(result.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registerd"));
	}
	
	// admin specific endpoints 
	
	

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
	@Secured("USER")
	public ResponseEntity<Termometre> addNewStation(
			@RequestBody TermometreDTO termometruToAdd,
			@RequestParam(name = "id", required = true) int id) {
		Termometre termometruToSave = modelMapper.map(termometruToAdd, Termometre.class);
		termometruAccess.save(termometruToSave);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping(path = "/house/termometre/update", consumes = "application/json")
	@Secured("USER")
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
	@Secured("ADMIN")
	public @ResponseBody ResponseEntity<List<Private>> getAllTheDevicesRegistered() {
		if (!privateDevicesAccess.findAll().isEmpty()) {
			return ResponseEntity.ok(privateDevicesAccess.findAll());
		} else {
			throw new EntityNotFoundException();
		}
		
	}

	@GetMapping(path = "/private/device/devicename/{deviceName}")
	@Secured({"ADMIN", "TEMPACCESS"})
	public @ResponseBody ResponseEntity<Iterable<Private>> getDeviceByName(
			@PathVariable String deviceName) {
		if (privateDevicesAccess.findByName(deviceName) != null) {
			return ResponseEntity.ok(privateDevicesAccess.findByName(deviceName));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/private/device/macaddress/{macAddress}")
	@Secured({"ADMIN", "TEMPACCESS"})
	public ResponseEntity<Iterable<Private>> getDeviceByMacAddress(
			@RequestParam() String macAddress) {
		if (privateDevicesAccess.findByMacaddress(macAddress) != null) {
			return ResponseEntity.ok(privateDevicesAccess.findByMacaddress(macAddress));
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/private/device/lastdate/{lastDateKnown}")
	@Secured({"ADMIN", "TEMPACCESS"})
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
	@Secured("USER")
	public @ResponseBody ResponseEntity<List<Stb>> getAllStbEntries() {
		if (!transportStbAccess.findAll().isEmpty()) {
			return ResponseEntity.ok(transportStbAccess.findAll());
		} else {
			throw new EntityNotFoundException();
		}
	}

	@GetMapping(path = "/transport/stb/statia/{statia}")
	@Secured("USER")
	public @ResponseBody ResponseEntity<List<Stb>> getStatie(
			@PathVariable String statia) {
		if (transportStbAccess.findByStatia(statia) != null) {
			return ResponseEntity.ok(transportStbAccess.findByStatia(statia));
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	@GetMapping(path = "/transport/stb/linia/{linia}")
	@Secured("USER")
	public @ResponseBody ResponseEntity<List<Stb>> getLinie(
			@PathVariable String linia) {
		if (transportStbAccess.findByLinia(linia) != null) {
			return ResponseEntity.ok(transportStbAccess.findByLinia(linia));
		} else {
			throw new EntityNotFoundException();
		}
	}
}
