package backend.application.services;

import backend.infrastructure.persistence.entities.TicketClerk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService ticketClerkRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TicketClerk ticketClerk = ticketClerkRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Ticket Clerk couldnt find!"));

        String role = ticketClerk.getRole();

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                ticketClerk.getEmail(),
                ticketClerk.getPassword(),
                authorities
        );
    }

}
