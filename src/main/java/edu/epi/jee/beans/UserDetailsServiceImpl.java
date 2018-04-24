package edu.epi.jee.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	// Put user credentials from a DB, RemeberMe,..
	private static Map<String, UserDetails> userRepository = new HashMap<String, UserDetails>();

	static {
		GrantedAuthority authorityAdmin = new GrantedAuthorityImpl("ROLE_ADMIN");
		GrantedAuthority authorityUser = new GrantedAuthorityImpl("ROLE_USER");

		// Create user with authorities
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(authorityAdmin);
		authorities.add(authorityUser);
		String username = "hela";
		UserDetails user = new UserDetailsImpl(username, "hela", authorities);
		userRepository.put(username, user);
	}

        @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails matchingUser = userRepository.get(username);
		if (matchingUser == null) {
			throw new UsernameNotFoundException("Username or password incorrect!");
		}
		return matchingUser;
	}
}
