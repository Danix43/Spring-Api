package danix43.api.models.databases.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import danix43.api.models.databases.UserRepository;
import danix43.api.modules.authentication.User;
import danix43.api.modules.authentication.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
		
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) {
		User user = (userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail))
				.orElseThrow(() -> new UsernameNotFoundException("User not found by this name or email: " + usernameOrEmail));
		
		return UserPrincipal.create(user);
	}
	
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("User not founds with that id: " + id));
		return UserPrincipal.create(user);
	}

}
