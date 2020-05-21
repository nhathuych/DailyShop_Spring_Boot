package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import com.nhathuy.dailyshopv2.service.DanhMucService;
import com.nhathuy.dailyshopv2.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SanPhamController {

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private DanhMucService danhMucService;

	@GetMapping("/product")
	public String product(ModelMap modelMap) {
		// mặc định chọn cái đầu tiên
		modelMap.addAttribute("sanphams", sanPhamService.findByIdDanhMuc(1, 0, 6));
		modelMap.addAttribute("danhmuc", danhMucService.findByStatus(true));
		return "product";
	}

	@GetMapping("/product/{metaTitle}")
	public String product(ModelMap modelMap, @PathVariable String metaTitle) {
		DanhMucSanPham danhMuc = danhMucService.findByMetaTitle(metaTitle);

		// phân trang
		int numOfProducts = 6;
		int numOfPages = (int) Math.ceil((double) sanPhamService.countByIdDanhMuc(danhMuc.getIdDanhMuc()) / numOfProducts);

		if (danhMuc != null) {
			modelMap.addAttribute("sanphams", sanPhamService.findByIdDanhMuc(danhMuc.getIdDanhMuc(), 0, numOfProducts));
			modelMap.addAttribute("danhmuc", danhMucService.findByStatus(true));
			modelMap.addAttribute("metaTitle", metaTitle);
			modelMap.addAttribute("numOfPages", numOfPages);
		} else {
			return "redirect:/404";
		}
		return "product";
	}

	@GetMapping("/product/{metaTitle}/{index}")
	public String product(ModelMap modelMap, @PathVariable("metaTitle") String metaTitle, @PathVariable("index") int index) {
		DanhMucSanPham danhMuc = danhMucService.findByMetaTitle(metaTitle);
		int numOfProducts = 6;
		int numOfPages = (int) Math.ceil((double) sanPhamService.countByIdDanhMuc(danhMuc.getIdDanhMuc()) / numOfProducts);

		if (danhMuc != null) {
			modelMap.addAttribute("sanphams", sanPhamService.findByIdDanhMuc(danhMuc.getIdDanhMuc(), numOfProducts * (index - 1), numOfProducts * index));
			System.out.println("--------- " + numOfProducts);
			modelMap.addAttribute("danhmuc", danhMucService.findByStatus(true));
			modelMap.addAttribute("metaTitle", metaTitle);
			modelMap.addAttribute("numOfPages", numOfPages);
		}
		return "product";
	}

	@PostMapping(value = "/product", params = "btnSearch")
	public String search(ModelMap modelMap, @RequestParam String key) {
		modelMap.addAttribute("sanphams", sanPhamService.findByTenSanPhamContains(key));
		return "product";
	}
}
