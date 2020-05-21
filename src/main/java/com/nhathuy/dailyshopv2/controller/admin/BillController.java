package com.nhathuy.dailyshopv2.controller.admin;

import com.nhathuy.dailyshopv2.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/bill")
public class BillController {

	@Autowired
	private HoaDonService hoaDonService;

	@GetMapping
	public String Default(ModelMap modelMap) {
		modelMap.addAttribute("dsBill", hoaDonService.findAll());
		return "admin-bill";
	}
}
