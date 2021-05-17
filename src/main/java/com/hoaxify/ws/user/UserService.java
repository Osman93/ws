package com.hoaxify.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        String pass = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }

    public Page<User> getUsers(Pageable page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(),page.getPageSize());
        return userRepository.findAll(pageable);
    }
}
