package com.dozee.userdetails.services;

import com.dozee.userdetails.exception.AuthenticationFailedException;
import com.dozee.userdetails.exception.UserAlreadyExistsException;
import com.dozee.userdetails.exception.UserNotFoundException;
import com.dozee.userdetails.model.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{
    boolean saveUser(UserDetails userDetails) throws UserAlreadyExistsException;

    public UserDetails findByEmailAndPassword(String email, String password) throws UserNotFoundException, AuthenticationFailedException;

    UserDetails updateUser(String emailId,UserDetails userDetails) throws UserNotFoundException;

    boolean deleteUserByEmailId(String email) throws UserNotFoundException;

    UserDetails getUserByEmail(String emailId)throws UserNotFoundException;

    List<UserDetails> getUserByCity(String city)throws UserNotFoundException;

    List<UserDetails> getUserByCountry(String country)throws UserNotFoundException;

    List<UserDetails> getUserByGender(String gender)throws UserNotFoundException;

    List<UserDetails> getAllUsers()throws UserNotFoundException;
}
