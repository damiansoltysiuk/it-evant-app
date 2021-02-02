package com.sda.iteventapp.service;

import com.sda.iteventapp.form.RegisterForm;
import com.sda.iteventapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User saveUser(RegisterForm registerForm);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
