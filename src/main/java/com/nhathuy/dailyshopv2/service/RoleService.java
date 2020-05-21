package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.Role;

public interface RoleService {
	Role findByName(String name);
	Role save(Role role);
}
