//package com.example.medplus.config;
//
//import com.example.medplus.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//public class UserDetailsServiceConfig {
//
//    private final UserService userService;
//
//    public UserDetailsServiceConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userService.getUserByEmail(username);
//    }
//}
