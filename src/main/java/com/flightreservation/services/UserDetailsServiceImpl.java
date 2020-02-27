package com.flightreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightreservation.entity.User;
import com.flightreservation.repos.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		User user2 = userRepository.findBYEmail(username);
		System.out.println("Sharadddddddd"+user);
		System.out.println("Sharad Dhumal"+user2);
		if(user == null) {
			throw new UsernameNotFoundException("User not found for the mail"+username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),user.getRoles());
	}

}
