package com.protasker.protasker_backend.security;

import com.protasker.protasker_backend.model.User;
import com.protasker.protasker_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    //This convert my user object to userdetails object and return
    public UserDetails loadUserByUsername(String username){
        String newUsername = username != null ? username.replace(" ", "_").toLowerCase() : null;
        User user = userRepository.findByUsernameOrEmail(newUsername,newUsername) .orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));
        return new CustomUserDetailsImpl(user);
    }

}
