package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
}
