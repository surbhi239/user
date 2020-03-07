package com.dozee.userdetails.controller;

import com.dozee.userdetails.exception.UserNotFoundException;
import com.dozee.userdetails.model.UserDetails;
import com.dozee.userdetails.services.SecurityTokenGenerator;
import com.dozee.userdetails.services.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@EnableWebMvc
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityTokenGenerator tokenGenerator;


    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody UserDetails userDetails) {

        try {
            userService.saveUser(userDetails);
            return new ResponseEntity<String>("{\"message\":\"" + "User registered successfully" + "\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> loginUser(@RequestBody UserDetails loginDetail) {
        try {
            String email = loginDetail.getEmail();
            String password = loginDetail.getPassword();

            if (email == null || password == null) {
                throw new Exception("Username and Password can not be empty");
            }

            UserDetails userDetails = userService.findByEmailAndPassword(email, password);
            if (userDetails == null) {
                throw new Exception("Username with given id does not exist");
            }
            Map<String, Object> map = tokenGenerator.generateToken(userDetails);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(path = "/updateUser", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUserByEmail(@RequestBody UserDetails userDetails, HttpServletRequest request) {
        ResponseEntity<?> responseEntity;
        final String authHeader = request.getHeader("Authorization");
        final String token = authHeader.substring(7);
        String emailId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
        try {
            final UserDetails fetchedUser = userService.updateUser(emailId, userDetails);
            responseEntity = new ResponseEntity<UserDetails>(fetchedUser, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}",
                    HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/deleteUser",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUserByEmailId(@RequestParam("emailId") final String emailId) {
        ResponseEntity<?> responseEntity;
        try {
            userService.deleteUserByEmailId(emailId);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }

        responseEntity = new ResponseEntity<String>("{\"message\":\"" + "User has been successfully deleted" + "\"}", HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/getUser",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchUsersByEmail(@RequestParam("emailId") final String emailId) {
        ResponseEntity<?> responseEntity;
        UserDetails userDetails = null;
        try {
            userDetails = userService.getUserByEmail(emailId);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/usersByCity",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchUsersByCity(@RequestParam("city") final String city) {
        ResponseEntity<?> responseEntity;
        List<UserDetails> userDetails = null;
        try {
            userDetails = userService.getUserByCity(city);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/usersByCountry",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchUsersByCountry(@RequestParam("country") final String country) {
        ResponseEntity<?> responseEntity;
        List<UserDetails> userDetails = null;
        try {
            userDetails = userService.getUserByCountry(country);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/usersByGender",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchUsersByGender(@RequestParam("gender") final String gender) {
        ResponseEntity<?> responseEntity;
        List<UserDetails> userDetails = null;
        try {
            userDetails = userService.getUserByGender(gender);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/allUsers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchAllUsers() {
        ResponseEntity<?> responseEntity;
        List<UserDetails> userDetails = null;
        try {
            userDetails = userService.getAllUsers();
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        responseEntity = new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
        return responseEntity;
    }

}
