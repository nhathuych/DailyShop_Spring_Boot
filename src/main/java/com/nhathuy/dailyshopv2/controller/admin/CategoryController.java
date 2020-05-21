package com.nhathuy.dailyshopv2.controller.admin;

import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import com.nhathuy.dailyshopv2.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private DanhMucService danhMucService;

	@GetMapping
	public String Default(ModelMap modelMap) {
		modelMap.addAttribute("categories", danhMucService.findAll());
		return "admin-category";
	}

	@PostMapping(params = "btnThemDanhMuc")
	public String themDanhMuc(ModelMap modelMap, @RequestParam String tendm, @RequestParam String metatitle, @RequestParam boolean status) {
		if (tendm.isEmpty()) {
			modelMap.addAttribute("msg", "Tên danh mục ko hợp lệ");
		} else if (danhMucService.findByMetaTitle(metatitle) != null) {
			modelMap.addAttribute("msg", "Meta Title đã tồn tại");
		} else if (metatitle.isEmpty()) {
			modelMap.addAttribute("msg", "Meta Title ko hợp lệ");
		} else {
			modelMap.addAttribute("categories", danhMucService.findAll());

			DanhMucSanPham danhmuc = new DanhMucSanPham();
			danhmuc.setTenDanhMuc(tendm);
			danhmuc.setMetaTitle(metatitle);
			danhmuc.setStatus(status);

			danhMucService.save(danhmuc);
			modelMap.addAttribute("msg", "Successfull");
		}
		return "admin-category";
	}

	@PostMapping(params = "btnSuaDanhMuc")
	public String capNhatDanhMuc(ModelMap modelMap, @RequestParam int madm, @RequestParam String tendm, @RequestParam String metatitle, @RequestParam boolean status) {
		if (tendm.isEmpty()) {
			modelMap.addAttribute("msg", "Tên danh mục ko hợp lệ");
		} else if (metatitle.isEmpty()) {
			modelMap.addAttribute("msg", "Meta Title ko hợp lệ");
		} else {
			modelMap.addAttribute("categories", danhMucService.findAll());

			DanhMucSanPham danhmuc = new DanhMucSanPham();
			danhmuc.setIdDanhMuc(madm);
			danhmuc.setTenDanhMuc(tendm);
			danhmuc.setMetaTitle(metatitle);
			danhmuc.setStatus(status);

			danhMucService.save(danhmuc);
			modelMap.addAttribute("msg", "Successfull");
		}
		return "admin-category";
	}
}
