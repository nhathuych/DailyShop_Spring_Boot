package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.entity.TokenVerification;
import com.nhathuy.dailyshopv2.entity.User;
import com.nhathuy.dailyshopv2.service.MailService;
import com.nhathuy.dailyshopv2.service.TokenVerificationService;
import com.nhathuy.dailyshopv2.service.UserService;
import com.nhathuy.dailyshopv2.util.VerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class PasswordController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenVerificationService tokenVerificationService;

	@Autowired
	private VerificationUtil verificationUtil;

	@Autowired
	private MailService mailService;

	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "forgot-password";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(ModelMap modelMap, @RequestParam String email) {
		if (email.trim().isEmpty()) {
			modelMap.addAttribute("msg", "Email is empty");
		} else {
			String registCode = verificationUtil.generateVerificationCode(email);
			Date expireDate = verificationUtil.calculatorExpireTime();
			User user = userService.findByEmail(email);
			if (user == null) {
				modelMap.addAttribute("msg", "Email not found");
				return "forgot-password";
			}

			mailService.send("Reset password", "/change-password", email, registCode, expireDate);
			TokenVerification tokenVerification = new TokenVerification(user, registCode, expireDate, 1);
			tokenVerificationService.save(tokenVerification);

			modelMap.addAttribute("msg", "Please check your email");
		}

		return "forgot-password";
	}

	@GetMapping("/change-password")
	public String giaoDien(ModelMap modelMap, @RequestParam String token) {
		TokenVerification tokenVerification = tokenVerificationService.findByTokenCode(token);
		if (tokenVerification == null) {
			modelMap.addAttribute("msg", "Token bị hết hạn");
		} else {
			Date now = new Date();
			// kiểm tra thời hạn token
			if (tokenVerification.getExpireTime().getTime() < now.getTime()) {
				tokenVerification.setExpireTime(verificationUtil.calculatorExpireTime());
				tokenVerification.setTokenCode(verificationUtil.generateVerificationCode(tokenVerification.getUser().getEmail()));
				tokenVerificationService.save(tokenVerification);
				mailService.send("Reset password", "/change-password", tokenVerification.getUser().getEmail(), tokenVerification.getTokenCode(), tokenVerification.getExpireTime());
				modelMap.addAttribute("msg", "Token bị hết hạn");
			}
			modelMap.addAttribute("tokenEmail", tokenVerification.getUser().getEmail());
			modelMap.addAttribute("tokenCode", token);
		}

		return "change-password";
	}

	@PostMapping("/change-password")
	public String changPassword(ModelMap modelMap, @RequestParam String tokenEmail, @RequestParam String tokenCode, @RequestParam(defaultValue = "") String newPassword, @RequestParam(defaultValue = "") String confirmPassword) {
		if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
			modelMap.addAttribute("msg", "There are empty field");
		} else if (!newPassword.equals(confirmPassword)) {
			modelMap.addAttribute("msg", "The password are not mismatched");
		} else {
			User user = userService.findByEmail(tokenEmail);
			user.setPassword(passwordEncoder.encode(newPassword));
			userService.save(user);

			// đổi mật khẩu thành công thì xóa token
			tokenVerificationService.deleteById(tokenVerificationService.findByTokenCode(tokenCode).getId());

			modelMap.addAttribute("msg", "Successfull");
		}
		return "change-password";
	}
}
