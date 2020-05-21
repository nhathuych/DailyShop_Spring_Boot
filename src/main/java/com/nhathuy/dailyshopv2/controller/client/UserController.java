package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.entity.Role;
import com.nhathuy.dailyshopv2.entity.User;
import com.nhathuy.dailyshopv2.service.RoleService;
import com.nhathuy.dailyshopv2.service.UserService;
import com.nhathuy.dailyshopv2.util.HuyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

@Controller
public class UserController {
	private static final String MSG_REGISTER = "msgRegister";

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String register(ModelMap modelMap, @RequestParam String fullName, @RequestParam String email, @RequestParam String password, @RequestParam String address) {
		String currEmail = email.trim().toLowerCase();
		if (currEmail.isEmpty()) {
			modelMap.addAttribute(MSG_REGISTER, "Email is empty!");
		} else if (!HuyUtil.isEmailValid(email)) {
			modelMap.addAttribute(MSG_REGISTER, "Email is invalid!");
		} else if (!HuyUtil.isEmailValid(currEmail)) {
			modelMap.addAttribute(MSG_REGISTER, "Email is invalid!");
		} else if (password.trim().isEmpty()) {
			modelMap.addAttribute(MSG_REGISTER, "Password is invalid!");
		} else {
			//Member account
			if (userService.findByEmail(currEmail) == null) {
				// member bình thường chỉ có 1 role
				HashSet<Role> roles = new HashSet<>();
				roles.add(roleService.findByName(Role.ROLE_MEMBER));
				userService.save(new User(currEmail, passwordEncoder.encode(password), fullName, address, roles));
				modelMap.addAttribute(MSG_REGISTER, "Successfull!");
			} else {
				modelMap.addAttribute(MSG_REGISTER, "That email address already exists. Please choose another!");
			}
		}
		return "login";
	}

	@GetMapping("/update-profile")
	public String updateProfile() {
		return  "update-profile";
	}

	@PostMapping("/update-profile")
	public String updateProfile(HttpSession httpSession, ModelMap modelMap, @RequestParam String fullName, @RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String address) {
		User user = (User) httpSession.getAttribute(HomeController.CURRENT_USER);
		if (fullName.trim().isEmpty()) {
			modelMap.addAttribute("msg", "Full name is empty");
		} else {
			user.setFullName(fullName);
			user.setAddress(address);
			if (!currentPassword.trim().isEmpty() || !newPassword.trim().isEmpty()) {
				user.setPassword(passwordEncoder.encode(newPassword));
			}
			userService.save(user);
			modelMap.addAttribute("msg", "Updated");
		}
		return "update-profile";
	}
}
