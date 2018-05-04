package edu.calstatela.jobsapi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calstatela.jobsapi.jwt.JwtAuthenticationResponse;
import edu.calstatela.jobsapi.jwt.JwtTokenProvider;
import edu.calstatela.jobsapi.model.Role;
import edu.calstatela.jobsapi.model.User;
import edu.calstatela.jobsapi.model.UserLogin;
import edu.calstatela.jobsapi.repository.UserRepository;
import edu.calstatela.jobsapi.validators.UserValidator;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    JwtTokenProvider tokenProvider;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
	
	@PostMapping("/user/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLogin userLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUserEmailId(),
                        userLogin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
	
	@GetMapping("/user/all-users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{userId}")
	@PostAuthorize("hasRole('ROLE_ADMIN') || returnObject.userId == principal.userId")
	public User getUser(@PathVariable(value = "userId") Integer userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
		return user;
	}
	 
	@PostMapping("/user/register")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);		
		if(bindingResult.hasErrors()) {
			System.out.println("Has Errors");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Role role = new Role();
		role.setRole("ROLE_USER");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println("User E: " + user.getFirstName());
		System.out.println("User E: " + user.getEmailId());
		userRepository.save(user);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/user/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') || principal.userId == #userId")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Integer userId) {
	    userRepository.deleteById(userId);
	    return ResponseEntity.ok().build();
	}

}
