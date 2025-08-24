//package com.example.medplus.service;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.example.medplus.dto.RegisterRequest;
//import com.example.medplus.model.User;
//import com.example.medplus.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public User register(RegisterRequest request) {
//        // Check if email already exists
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
//        }
//
//        // Create new user
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setEmail(request.getEmail());
//        user.setFullName(request.getFullName());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(request.getRole());
//
//        return userRepository.save(user);
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User appUser = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        List<SimpleGrantedAuthority> authorities = List.of(
//            new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name())
//        );
//
//        return new org.springframework.security.core.userdetails.User(
//                appUser.getEmail(),
//                appUser.getPassword(),
//                authorities
//        );
//    }
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//}

//Ranjit
package com.example.medplus.service;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.medplus.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

	}
}
