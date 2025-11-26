package com.usermicroservice.users.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermicroservice.users.Models.User;
import com.usermicroservice.users.Repository.IUserRepository;

@Service
public class UserService {
    
    private IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public User createUser(User userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());

        // Hash password prima di salvare
        String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
        user.setPassword(hashedPassword);

        user.setNome(userDetails.getNome());
        user.setCognome(userDetails.getCognome());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
        
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setNome(userDetails.getNome());
        user.setCognome(userDetails.getCognome());
        
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
        userRepository.delete(user);
    }
}
