package com.nhathuy.dailyshopv2.service;

import java.util.Date;

public interface MailService {
	void send(String title, String url, String emailUser, String registCode, Date expireDate);
}
