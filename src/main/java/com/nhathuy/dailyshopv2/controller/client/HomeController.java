package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.service.SanPhamService;
import com.nhathuy.dailyshopv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
	public static final String CURRENT_USER = "currentUser";

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(HttpSession httpSession, ModelMap modelMap, Principal principal) {
		if (principal != null && httpSession.getAttribute(CURRENT_USER) == null) {
			httpSession.setAttribute(CURRENT_USER, userService.findByEmail(principal.getName()));
		}
		modelMap.addAttribute("sanphams", sanPhamService.findSanPham(0, 8));
		return "index";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@GetMapping("/login")
	public String login(HttpSession httpSession) {
		return httpSession.getAttribute(CURRENT_USER) == null ? "login" : "redirect:/";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
		httpSession.removeAttribute(CURRENT_USER);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}
