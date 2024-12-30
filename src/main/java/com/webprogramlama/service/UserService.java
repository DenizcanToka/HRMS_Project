package com.webprogramlama.service;

import com.webprogramlama.common.GeneralException;
import com.webprogramlama.controller.dto.LoginRequest;
import com.webprogramlama.controller.dto.UserDto;
import com.webprogramlama.model.User;
import com.webprogramlama.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws GeneralException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new GeneralException("User with email already exists.");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) throws GeneralException {
        return userRepository.findById(id).orElseThrow(() -> new GeneralException("User not found."));
    }

    public void deleteUser(Integer id) throws GeneralException {
        if (!userRepository.existsById(id)) {
            throw new GeneralException("User does not exist.");
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    public void register(UserDto userDto) {
        // Email kontrolü (kullanıcı zaten var mı?)
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use!");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Şifreyi encode etmelisin
        user.setRole("USER"); // Varsayılan rol atama
        userRepository.save(user);
    }
    public boolean isUsernameValid(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean authenticate(LoginRequest loginRequest) {
        return true;//"admin".equals(loginRequest.getUsername())
                //&& "password".equals(loginRequest.getPassword());
    }
}