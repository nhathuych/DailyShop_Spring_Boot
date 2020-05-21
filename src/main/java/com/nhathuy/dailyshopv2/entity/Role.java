package com.nhathuy.dailyshopv2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ROLE_MEMBER = "MEMBER";
	public static final String ROLE_ADMIN = "ADMIN";
	//public static final String ROLE_MANAGER = "MANAGER";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Set<User> users;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
