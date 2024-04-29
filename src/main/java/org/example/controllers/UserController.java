package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.dto.UsersDto;
import org.example.exceptions.UserAlreadyExistedException;
import org.example.mapper.UserMapper;
import org.example.models.User;
import org.example.request.FindByEmailRequest;
import org.example.request.UserRequest;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    public UsersDto findAll() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = userMapper.map(users);
        return new UsersDto(userDtos);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @GetMapping("/findByEmail")
    public UserDto findByEmail(@RequestBody FindByEmailRequest emailRequest) {
        User user = userService.findByEmail(emailRequest.getEmail());
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @PostMapping("")
    public UserDto save(@RequestBody UserRequest userRequest) {
        User user = userMapper.map(userRequest);
        user = userService.save(user);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteById(@PathVariable Long id) {
        User user = userService.deleteById(id);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = userMapper.map(id, userRequest);
        user = userService.update(user);
        if (user == null) {
            return null;
        }
        return userMapper.map(user);
    }
}
