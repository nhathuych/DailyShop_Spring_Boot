package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.User;
import com.nhathuy.dailyshopv2.repository.UserRepository;
import com.nhathuy.dailyshopv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // annotation giúp ta khi gọi userService nó tự khởi tạo userService = new UserServiceImpl()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
