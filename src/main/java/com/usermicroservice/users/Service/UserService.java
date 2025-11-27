package com.usermicroservice.users.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermicroservice.users.DTO.UserMapper;
import com.usermicroservice.users.DTO.UserRequestDTO;
import com.usermicroservice.users.DTO.UserResponseDTO;
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
    
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Converti DTO in Entity
        User user = UserMapper.toEntity(userRequestDTO);
        
        // Hash password prima di salvare
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        // Salva l'utente
        User savedUser = userRepository.save(user);
        
        // Converti e ritorna DTO response
        return UserMapper.toResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
            .map(UserMapper::toResponseDTO);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
        
        // Aggiorna i campi dall'DTO
        UserMapper.updateEntityFromDTO(user, userRequestDTO);
        
        // Hash password se è stata modificata
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
            user.setPassword(hashedPassword);
        }
        
        // Salva e ritorna DTO response
        User updatedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
        userRepository.delete(user);
    }
}
