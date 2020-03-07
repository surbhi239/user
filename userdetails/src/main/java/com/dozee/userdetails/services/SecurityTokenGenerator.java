package com.dozee.userdetails.services;

import com.dozee.userdetails.model.UserDetails;

import java.util.Map;

public interface SecurityTokenGenerator {

	Map<String, Object> generateToken(UserDetails manager);
}
