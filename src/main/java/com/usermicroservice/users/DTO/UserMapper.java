package com.usermicroservice.users.DTO;

import com.usermicroservice.users.Models.User;

/**
 * Mapper per convertire tra Entity User e DTO
 */
public class UserMapper {
    
    /**
     * Converte UserRequestDTO in User entity
     */
    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setNome(dto.getNome());
        user.setCognome(dto.getCognome());
        
        return user;
    }
    
    /**
     * Converte User entity in UserResponseDTO
     */
    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getNome(),
            user.getCognome(),
            user.getDataCreazione()
        );
    }
    
    /**
     * Aggiorna un'entità User esistente con i dati del DTO
     */
    public static void updateEntityFromDTO(User user, UserRequestDTO dto) {
        if (user == null || dto == null) {
            return;
        }
        
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getNome() != null) {
            user.setNome(dto.getNome());
        }
        if (dto.getCognome() != null) {
            user.setCognome(dto.getCognome());
        }
    }
}
