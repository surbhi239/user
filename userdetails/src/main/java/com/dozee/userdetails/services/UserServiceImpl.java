        package com.dozee.userdetails.services;

import com.dozee.userdetails.exception.AuthenticationFailedException;
import com.dozee.userdetails.exception.UserAlreadyExistsException;
import com.dozee.userdetails.exception.UserNotFoundException;
import com.dozee.userdetails.model.UserDetails;
import com.dozee.userdetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final transient UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUser(UserDetails userDetails) throws UserAlreadyExistsException {
        Optional<UserDetails> ud = userRepository.findByEmail(userDetails.getEmail());
        if (ud.isPresent()) {
            throw new UserAlreadyExistsException("User with email already exist");
        }
        UserDetails user = new UserDetails();

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAge(userDetails.getAge());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());
        user.setGender(userDetails.getGender());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setEmail(userDetails.getEmail());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setRole(userDetails.getRole());
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails findByEmailAndPassword(String email, String password) throws UserNotFoundException, AuthenticationFailedException {
        Optional<UserDetails> optionalUserDetails = userRepository.findByEmail(email);
        UserDetails userDetails;
        if(optionalUserDetails.isPresent()) {
            userDetails = optionalUserDetails.get();
        } else {
            throw new UserNotFoundException("User id not found in database");
        }
        if(passwordEncoder.matches(password,userDetails.getPassword())){
            return userDetails;
        } else {
         throw new AuthenticationFailedException(" Unable to Authenticate User");
        }
    }

    @Override
    public UserDetails updateUser(String emailId, final UserDetails updateMovie) throws UserNotFoundException {

        final UserDetails userDetails = userRepository.findByEmail(emailId).orElse(null);
        if (userDetails == null) {
            throw new UserNotFoundException("Can't update user, UserDetails is not found");
        }
        userDetails.setFirstName(updateMovie.getFirstName());
        userDetails.setLastName(updateMovie.getLastName());
        userDetails.setAge(updateMovie.getAge());
        userDetails.setCity(updateMovie.getCity());
        userDetails.setCountry(updateMovie.getCountry());
        userDetails.setGender(updateMovie.getGender());
        userDetails.setPhoneNumber(updateMovie.getPhoneNumber());
        userDetails.setEmail(updateMovie.getEmail());
        userDetails.setPassword(passwordEncoder.encode(updateMovie.getPassword()));
        userDetails.setRole(updateMovie.getRole());
        userRepository.save(userDetails);
        return userDetails;
    }

    @Override
    public boolean deleteUserByEmailId(final String emailId) throws UserNotFoundException {

        final UserDetails userDetails = userRepository.findByEmail(emailId).orElse(null);
        if (userDetails == null) {
            throw new UserNotFoundException("Can't delete movie, UserDetails is not found");
        }
        userRepository.delete(userDetails);
        return true;
    }

    @Override
    public UserDetails getUserByEmail(String emailId) throws UserNotFoundException {
        Optional<UserDetails> optionalUserDetails = userRepository.findByEmail(emailId);
        UserDetails userDetails;
        if(optionalUserDetails.isPresent()) {
            userDetails = optionalUserDetails.get();
        } else {
            throw new UserNotFoundException("User id not found in database");
        }
        return userDetails;
    }

    @Override
    public List<UserDetails> getUserByCity(String city) throws UserNotFoundException {
        final List<UserDetails> userDetails = userRepository.findByCity(city);
        if (userDetails == null) {
            throw new UserNotFoundException("UserDetails  not found");
        }
        return userDetails;
    }

    @Override
    public List<UserDetails> getUserByCountry(String country) throws UserNotFoundException {
        final List<UserDetails> userDetails = userRepository.findByCountry(country);
        if (userDetails == null) {
            throw new UserNotFoundException("UserDetails  not found");
        }
        return userDetails;
    }

    @Override
    public List<UserDetails> getUserByGender(String gender) throws UserNotFoundException {
        final List<UserDetails> userDetails = userRepository.findByGender(gender);
        if (userDetails == null) {
            throw new UserNotFoundException("UserDetails not found");
        }
        return userDetails;
    }
    @Override
    public List<UserDetails> getAllUsers() throws UserNotFoundException {
        final List<UserDetails> userDetails = userRepository.findAll();
        if (userDetails == null) {
            throw new UserNotFoundException("UserDetails not found");
        }
        return userDetails;
    }
}
