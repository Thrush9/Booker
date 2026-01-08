package com.application.booker.service;

import com.application.booker.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    UserDetails loadUserByUsername(String username);

    Optional<Users> findUserByUsername(String username);

}
