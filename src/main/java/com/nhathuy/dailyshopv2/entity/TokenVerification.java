package com.nhathuy.dailyshopv2.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token_verfication")
public class TokenVerification {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;

	@Column(name = "token_code", nullable = false)
	private String tokenCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time", nullable = false)
	private Date expireTime;

	@Column(name = "type", nullable = false)
	private Integer type;

	public TokenVerification() {
	}

	public TokenVerification(User user, String tokenCode, Date expireTime, Integer type) {
		this.user = user;
		this.tokenCode = tokenCode;
		this.expireTime = expireTime;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}