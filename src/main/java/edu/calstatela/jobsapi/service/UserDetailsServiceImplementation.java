package edu.calstatela.jobsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.calstatela.jobsapi.model.User;
import edu.calstatela.jobsapi.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userEmaidId) throws UsernameNotFoundException {
		User user = userRepository.findByemailId(userEmaidId);
		if(user == null) {
			throw new UsernameNotFoundException("User doesn't exist");
		}
		return User.create(user);
	}
	
	public User loadUser(String userEmaidId) {
		User user = userRepository.findByemailId(userEmaidId);
		return user;
	}

}
