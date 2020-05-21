package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.User;

public interface UserService {
	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	User save(User user);
}
