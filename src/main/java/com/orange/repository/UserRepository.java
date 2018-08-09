package com.orange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
