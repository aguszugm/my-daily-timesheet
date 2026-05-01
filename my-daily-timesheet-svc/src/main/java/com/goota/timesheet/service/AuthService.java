package com.goota.timesheet.service;

import com.goota.timesheet.dto.AuthRequest;
import com.goota.timesheet.dto.AuthResponse;
import com.goota.timesheet.dto.UserRegistrationRequest;
import com.goota.timesheet.dto.UserResponse;
import com.goota.timesheet.entity.User;
import com.goota.timesheet.repository.UserRepository;
import com.goota.timesheet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public UserResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByUserEmail(request.getUserEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        long nextId = userRepository.findAll()
                .stream().mapToLong(User::getId).max().orElse(0L) + 1L;

        User user = User.builder()
                .id(nextId)
                .userEmail(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .admin(request.isAdmin())
                .build();

        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getUserEmail(), saved.getFullName(), saved.isAdmin());
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserEmail());
        String token = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUserEmail(request.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new AuthResponse(token, user.getFullName(), user.isAdmin());
    }
}
