package com.nhathuy.dailyshopv2.repository;

import com.nhathuy.dailyshopv2.entity.TokenVerification;
import org.springframework.data.repository.CrudRepository;

public interface TokenVerifitionRepository extends CrudRepository<TokenVerification, Integer> {
	TokenVerification findByTokenCode(String tokenCode);
}
