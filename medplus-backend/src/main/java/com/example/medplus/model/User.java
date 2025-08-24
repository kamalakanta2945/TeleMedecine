//package com.example.medplus.model;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.Data;
//import com.example.medplus.util.Role;
//
//
//@Data
//@Document(collection = "users")
//public class User implements UserDetails {
//    @Id
//    private String id;
//    private String username;
//    private String email;
//    private String password;  // Hashed
//    private String fullName;
//    private Role role;  // PATIENT or DOCTOR
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}


//Ranjit

package com.example.medplus.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import com.example.medplus.util.Role;

@Data
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String username;   // Optional (can be used for display name)
    private String email;
    private String password;   // Hashed
    private String fullName;
    private Role role;         // PATIENT or DOCTOR

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    // 🔑 Important: use email as the principal username
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
