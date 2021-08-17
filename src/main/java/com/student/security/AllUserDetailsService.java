package com.student.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.student.entities.User;
import com.student.repositories.UserRepository;

@Component
public class AllUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User users = userRepository.findByUsername(userName);
		if(users == null)
			new UsernameNotFoundException("Username"+":"+userName+"not found");
		
		
		return UserPrincipal.create(users);
	}
	@Transactional
	public UserDetails loadUserById(Integer id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return UserPrincipal.create(user);

}
}