package com.sda.iteventapp.service.impl;

import com.sda.iteventapp.exception.UserNotFoundException;
import com.sda.iteventapp.form.RegisterForm;
import com.sda.iteventapp.model.User;
import com.sda.iteventapp.model.UserRole;
import com.sda.iteventapp.repository.UserRepository;
import com.sda.iteventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(RegisterForm registerForm) {
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setUsername(registerForm.getUsername());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setRole(Arrays.asList(UserRole.USER));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existed = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        existed.setUsername(user.getUsername());
        existed.setPassword(passwordEncoder.encode(user.getPassword()));
        existed.setEmail(user.getEmail());
        existed.setRole(user.getRole());
        return userRepository.save(existed);
    }

    public void deleteUser(Long id) {
        User existed = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(existed);
    }
}
