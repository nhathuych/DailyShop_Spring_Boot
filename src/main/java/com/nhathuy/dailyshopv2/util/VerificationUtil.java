package com.nhathuy.dailyshopv2.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class VerificationUtil {

	@Autowired
	PasswordEncoder encoder;

	public String generateVerificationCode(String emailAndPassword) {
		Date now = new Date();
		return encoder.encode(emailAndPassword + now.getTime());
	}

	public Date calculatorExpireTime() {
		Date now = new Date();
		long time = now.getTime() + 86400000;
		return new Date(time);
	}
}
