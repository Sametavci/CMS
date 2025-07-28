package backend.domain.controllers;

import backend.application.services.AuthService;
import backend.application.services.UserDetailsServiceImpl;
import backend.infrastructure.api.dto.AuthRequest;
import backend.infrastructure.api.dto.AuthResponse;
import backend.infrastructure.config.JwtUtil;
import backend.infrastructure.persistence.entities.TicketClerk;
import backend.infrastructure.persistence.mapper.TicketClerkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsServiceImpl userDetailsService;
    @Autowired private AuthService authService;
    @Autowired private final PasswordEncoder passwordEncoder;
    @Autowired private final TicketClerkMapper ticketClerkMapper;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kullanıcı adı veya şifre");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        Optional<TicketClerk> userOpt = authService.findByUsername(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı bulunamadı");
        }

        TicketClerk user = userOpt.get();
        String role = user.getRole();

        String token = jwtUtil.generateToken(user.getEmail(), role);

        System.out.println("Generated Token: " + token);

        AuthResponse response = new AuthResponse(
                user.getId(),
                user.getEmail(),
                token,
                role
        );

        return ResponseEntity.ok(response);
    }

}