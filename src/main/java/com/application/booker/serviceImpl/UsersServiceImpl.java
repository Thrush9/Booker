package com.application.booker.serviceImpl;

import com.application.booker.entity.Users;
import com.application.booker.repository.UsersRepository;
import com.application.booker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username NotFound"));
    }

    @Override
    public Optional<Users> findUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
