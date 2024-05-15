package com.mitocode.security;

import com.mitocode.model.User;
import com.mitocode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for validate the users
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    /**
     * This method permit find a user and put the roles
     * @param username is the email
     * @return A user Details this class is from spring
     * @throws UsernameNotFoundException can throw an exception
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.repository.findOneByUsername(username);

        if(user == null) throw new UsernameNotFoundException("User not found: " + username);

        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(r->{
            roles.add(new SimpleGrantedAuthority(r.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
