package com.dozee.userdetails.services;

import com.dozee.userdetails.model.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	public SecurityTokenGeneratorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Object> generateToken(UserDetails manager) {
		// TODO Auto-generated method stub

		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(manager.getEmail()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, Object> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "You are successfully logged in");
		map.put("user details",manager);
		return map;
	}

}
