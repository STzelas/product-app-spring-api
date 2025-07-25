package com.stzelas.gr.spring_api.services;

import com.stzelas.gr.spring_api.model.User;
import com.stzelas.gr.spring_api.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser (User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

//    public boolean authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username);
//
//        if(!user.getUsername().equals(username)){
//            throw new UsernameNotFoundException("User does not exist in the database");
//        }
//
//        if (!bCryptPasswordEncoder.matches(password, user.getPasswordHash())) {
//            throw  new BadCredentialsException("The password is incorrect");
//        }
//
//        return  true;
//    }


}
