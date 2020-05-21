package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;
import com.nhathuy.dailyshopv2.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChiTietSanPhamController {

	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;

	@GetMapping("/product-detail/{idSanPham}")
	public String productDetail(ModelMap modelMap, @PathVariable int idSanPham) {
		Iterable<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamService.findByIdSanPham(idSanPham);
		modelMap.addAttribute("sanpham", chiTietSanPhams.iterator().next().getSanpham());
		modelMap.addAttribute("chitiets", chiTietSanPhams);
		return "product-detail";
	}
}
