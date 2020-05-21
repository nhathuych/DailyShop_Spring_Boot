package com.nhathuy.dailyshopv2.config;

import com.nhathuy.dailyshopv2.entity.Role;
import com.nhathuy.dailyshopv2.entity.User;
import com.nhathuy.dailyshopv2.service.RoleService;
import com.nhathuy.dailyshopv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final String EMAIL_ADMIN = "admin@gmail.com";

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		// Roles
		if (roleService.findByName(Role.ROLE_ADMIN) == null) {
			roleService.save(new Role(Role.ROLE_ADMIN));
		}
		if (roleService.findByName(Role.ROLE_MEMBER) == null) {
			roleService.save(new Role(Role.ROLE_MEMBER));
		}

		// Admin account
		if (userService.findByEmail(EMAIL_ADMIN) == null) {
			// admin sẽ có đầy đử tất cả các role
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleService.findByName(Role.ROLE_ADMIN));
			roles.add(roleService.findByName(Role.ROLE_MEMBER));
			userService.save(new User(EMAIL_ADMIN, passwordEncoder.encode("123456"), "Admin", "Asian Tech", roles));
		}
	}
}
