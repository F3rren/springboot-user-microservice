package com.usermicroservice.users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.users.Models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    
    
}
