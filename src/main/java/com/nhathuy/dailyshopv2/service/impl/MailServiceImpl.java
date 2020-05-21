package com.nhathuy.dailyshopv2.service.impl;

import com.nhathuy.dailyshopv2.service.MailService;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void send(String title, String url, String emailUser, String registCode, Date expireDate) {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper messageHelper = null;
		try {
			messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
			Template t = freemarkerConfig.getTemplate("email-template.ftl");

			Map<String, Object> model = new HashMap<>();
			model.put("expireDate", expireDate.toString());
			model.put("href", url);
			model.put("token", registCode);

			String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			messageHelper.setSubject(title);
			messageHelper.setText(mailContent, true);
			messageHelper.setTo(emailUser);

			javaMailSender.send(message);
		} catch (MessagingException | IOException | TemplateException e) {
			System.out.println("--- error: " + e.getMessage());
		}
	}
}
