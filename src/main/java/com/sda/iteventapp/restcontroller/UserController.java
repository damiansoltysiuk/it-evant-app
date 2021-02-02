package com.sda.iteventapp.restcontroller;

import com.sda.iteventapp.dto.UserDto;
import com.sda.iteventapp.exception.UserNotFoundException;
import com.sda.iteventapp.form.RegisterForm;
import com.sda.iteventapp.form.util.Error;
import com.sda.iteventapp.form.util.ValidatorUtil;
import com.sda.iteventapp.mapper.UserMapper;
import com.sda.iteventapp.model.User;
import com.sda.iteventapp.repository.UserRepository;
import com.sda.iteventapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sda.iteventapp.form.util.ErrorNamespace.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Return all users")
    @GetMapping("")
    public ResponseEntity all() {
        List<User> userList = userService.findAll();
        List<UserDto> userDtoList = UserMapper.MAPPER.listUserDto(userList);
        return ResponseEntity.ok(userDtoList);
    }

    @ApiOperation(value = "Return user by id")
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserDto userDto = UserMapper.MAPPER.userToUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(value = "Add user")
    @PostMapping("")
    public ResponseEntity save(@RequestBody RegisterForm registerForm, HttpServletRequest request) {
        List<Error> errors = validateCredentials(registerForm);
        if (errors.size() > 0) {
            return ResponseEntity.status(400).body(errors);
        }

        User saved = userService.saveUser(registerForm);
        UserDto userDto = UserMapper.MAPPER.userToUserDto(saved);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/api/user/")
                        .buildAndExpand(userDto.getId())
                        .toUri()
        ).build();
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private List<Error> validateCredentials(RegisterForm registerForm) {
        List<Error> result = new ArrayList<>();
        if (!ValidatorUtil.isCorrectEmail(registerForm.getEmail())) {
            result.add(EMAIL_INVALID);
        }

        if (!registerForm.getPassword().equals(registerForm.getRepeatedPassword())) {
            result.add(PASSWORD_IDENTITY);
        }

        if (!ValidatorUtil.isCorrectPassword(registerForm.getPassword(), 8, 30)) {
            result.add(PASSWORD_LENGTH);
        }

        if (!ValidatorUtil.isUsernameCorrect(registerForm.getUsername())) {
            result.add(USERNAME_VALIDATE);
        }

        if (result.size() > 0) {
            return result;
        }

        Optional<User> byEmail = userRepository.findByEmail(registerForm.getEmail());
        if (byEmail.isPresent()) {
            result.add(EMAIL_USED);
        }

        Optional<User> byUsername = userRepository.findByUsername(registerForm.getUsername());
        if (byUsername.isPresent()) {
            result.add(USERNAME_USED);
        }
        return result;
    }
}
