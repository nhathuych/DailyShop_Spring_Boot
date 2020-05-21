package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.TokenVerification;

public interface TokenVerificationService {
	void save(TokenVerification tokenVerification);
	TokenVerification findByTokenCode(String tokenCode);
	void deleteById(Integer id);
}
