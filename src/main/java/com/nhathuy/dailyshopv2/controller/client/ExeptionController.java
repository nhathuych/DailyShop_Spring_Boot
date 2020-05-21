package com.nhathuy.dailyshopv2.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExeptionController {

	@GetMapping("/404")
	public String exeption() {
		return "404";
	}
}
