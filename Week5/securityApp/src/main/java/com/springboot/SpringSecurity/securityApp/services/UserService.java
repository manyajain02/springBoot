package com.springboot.SpringSecurity.securityApp.services;

import com.springboot.SpringSecurity.securityApp.dto.SignUpDto;
import com.springboot.SpringSecurity.securityApp.dto.UserDto;

import com.springboot.SpringSecurity.securityApp.entities.User;
import com.springboot.SpringSecurity.securityApp.exceptions.ResourceNotFoundException;
import com.springboot.SpringSecurity.securityApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email "+ username +" not found"));
    }



    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
                " not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
       Optional<User> user =  userRepository.findByEmail(signUpDto.getEmail());
       if (user.isPresent()){
           throw new BadCredentialsException("User with email already exits" + signUpDto.getEmail());

       }
       User toBeCreatedUser = modelMapper.map(signUpDto,User.class);
       toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
       User savedUser= userRepository.save(toBeCreatedUser);

       return modelMapper.map(savedUser, UserDto.class);
    }



//    public User getUserById(Long userId) {
//        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id "+ userId +
//                " not found"));
//    }
//
//    public User getUsrByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }


//    public UserDto signUp(SignUpDto signUpDto) {
//        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
//        if(user.isPresent()) {
//            throw new BadCredentialsException("User with email already exits "+ signUpDto.getEmail());
//        }
//
//        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
//        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
//
//        User savedUser = userRepository.save(toBeCreatedUser);
//        return modelMapper.map(savedUser, UserDto.class);
//    }

//    public User save(User newUser) {
//        return userRepository.save(newUser);
//    }
}






















