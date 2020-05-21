package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByName(String name);
	Role save(Role role);
}
