package com.dozee.userdetails.repository;

import com.dozee.userdetails.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

    Optional<UserDetails> findByEmail(String email);
    List<UserDetails> findByCity(String city);
    List<UserDetails> findByCountry(String country);
    List<UserDetails> findByGender(String gender);
}
