package org.example.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.PasswordEncoder;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        //user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User update(User user){
        User existingUser=userRepository.findById(user.getId()).orElse(null);
        assert existingUser != null;
        existingUser= user;
        return userRepository.save(existingUser);
    }

    public User deleteById(Long id){
        User user = findById(id);
        if (user == null) {
            return null;
        }
        userRepository.deleteById(id);
        return user;
    }
}
