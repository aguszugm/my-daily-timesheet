package com.goota.timesheet.service;

import com.goota.timesheet.dto.UserResponse;
import com.goota.timesheet.dto.UserUpdateRequest;
import com.goota.timesheet.entity.User;
import com.goota.timesheet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserResponse(u.getId(), u.getUserEmail(), u.getFullName(), u.isAdmin()))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return new UserResponse(user.getId(), user.getUserEmail(), user.getFullName(), user.isAdmin());
    }

    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setAdmin(request.isAdmin());
        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getUserEmail(), saved.getFullName(), saved.isAdmin());
    }
}
