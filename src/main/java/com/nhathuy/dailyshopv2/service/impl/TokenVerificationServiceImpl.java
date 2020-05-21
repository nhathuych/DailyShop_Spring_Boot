package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.entity.TokenVerification;
import com.nhathuy.dailyshopv2.repository.TokenVerifitionRepository;
import com.nhathuy.dailyshopv2.service.TokenVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {

	@Autowired
	private TokenVerifitionRepository tokenVerifitionRepository;

	@Override
	public void save(TokenVerification tokenVerification) {
		tokenVerifitionRepository.save(tokenVerification);
	}

	@Override
	public TokenVerification findByTokenCode(String tokenCode) {
		return tokenVerifitionRepository.findByTokenCode(tokenCode);
	}

	@Override
	public void deleteById(Integer id) {
		tokenVerifitionRepository.deleteById(id);
	}
}
