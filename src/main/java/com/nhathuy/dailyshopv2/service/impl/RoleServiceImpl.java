package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.Role;
import com.nhathuy.dailyshopv2.repository.RoleRepository;
import com.nhathuy.dailyshopv2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
