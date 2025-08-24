//package com.example.medplus.controller;
//
//import com.example.medplus.dto.LoginRequest;
//import com.example.medplus.dto.RegisterRequest;
//import com.example.medplus.model.User;
//import com.example.medplus.service.JwtService;
//import com.example.medplus.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserService userService;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    // REGISTER NEW USER
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        User user = userService.register(request);
//        String jwt = jwtService.generateToken(user);
//        Map<String, String> response = new HashMap<>();
//        response.put("token", jwt);
//        response.put("role", user.getRole().name());
//        return ResponseEntity.ok(response);
//    }
//
//    // LOGIN EXISTING USER
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        Authentication auth = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        User user = userService.getUserByEmail(request.getEmail());
//        String jwt = jwtService.generateToken(user);
//
//        Map<String, String> response = new HashMap<>();
//        response.put("token", jwt);
//        response.put("role", user.getRole().name());
//
//        return ResponseEntity.ok(response);
//    }
//}

//Ranjit
package com.example.medplus.controller;

import com.example.medplus.dto.LoginRequest;
import com.example.medplus.dto.LoginResponse;
import com.example.medplus.dto.RegisterRequest;
import com.example.medplus.model.User;
import com.example.medplus.repository.UserRepository;
import com.example.medplus.service.UserService;
import com.example.medplus.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    
    
 // REGISTER
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new LoginResponse(null, null, "Email already taken"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        // generate token immediately after register
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        user.getRole().name(),
                        "User registered successfully"
                )
        );
    }

    
 // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        user.getRole().name(),   // send role
                        "Login successful"
                )
        );
    }


//    // LOGIN
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        var user = (User) authentication.getPrincipal();
//        String token = jwtService.generateToken(user);
//
//        return ResponseEntity.ok(new LoginResponse(token));
//    }
//
//    // REGISTER
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().body("Email already taken");
//        }
//
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(request.getRole());
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok("User registered successfully");
//    }
}


