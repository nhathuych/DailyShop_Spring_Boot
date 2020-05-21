package com.nhathuy.dailyshopv2.controller.admin;

import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;
import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import com.nhathuy.dailyshopv2.entity.SanPham;
import com.nhathuy.dailyshopv2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/product")
public class SanPhamAdminController {

	@Autowired
	private DanhMucService danhMucService;

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private SizeSanPhamService sizeSanPhamService;

	@Autowired
	private MauSanPhamService mauSanPhamService;

	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;

	@GetMapping
	public String Default(ModelMap modelMap) {
		modelMap.addAttribute("danhmuc", danhMucService.findAll());
		modelMap.addAttribute("tendanhmuc", "Chọn danh mục");
		modelMap.addAttribute("pages", 1);    // bắt đầu từ 1
		return "admin-product";
	}

	private void daydu(ModelMap modelMap, String metaTitle) {
		DanhMucSanPham danhmuc = danhMucService.findByMetaTitle(metaTitle);

		// phân trang
		int numOfProducts = 4;
		int pages = (int) Math.ceil((double) sanPhamService.countByIdDanhMuc(danhmuc.getIdDanhMuc()) / numOfProducts);

		modelMap.addAttribute("danhmuc", danhMucService.findAll());
		modelMap.addAttribute("tendanhmuc", metaTitle);
		modelMap.addAttribute("sanphams", sanPhamService.findByIdDanhMuc(danhmuc.getIdDanhMuc(), 0, numOfProducts));
		modelMap.addAttribute("pages", pages);
		modelMap.addAttribute("idDanhMuc", danhmuc.getIdDanhMuc());
		modelMap.addAttribute("sizes", sizeSanPhamService.findAll());
		modelMap.addAttribute("colors", mauSanPhamService.findAll());
	}

	@GetMapping("/{metaTitle}")
	public String Default(ModelMap modelMap, @PathVariable String metaTitle) {
		daydu(modelMap, metaTitle);
		return "admin-product";
	}

	@PostMapping("/{metaTitle}")
	public String themSanPham(@RequestParam(defaultValue = "-1") int masp, @RequestParam String tensp, @RequestParam String danhmucsp, @RequestParam int giasp
			, @RequestParam String gioitinh, @RequestParam String mota, @RequestParam MultipartFile hinhsp
			, ModelMap modelMap, @PathVariable String metaTitle) {
		daydu(modelMap, metaTitle);

		if (tensp.trim().isEmpty()) {
			modelMap.addAttribute("msgThemSanPham", "Tên sản phẩm trống");
		} else if (mota.trim().isEmpty()) {
			modelMap.addAttribute("msgThemSanPham", "Thiếu mô tả");
		} else if (hinhsp.isEmpty()) {
			modelMap.addAttribute("msgThemSanPham", "Thiếu hình ảnh");
		} else {
			try {
				System.out.println(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product\\" + hinhsp.getOriginalFilename()));
				FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product\\" + hinhsp.getOriginalFilename()));
				fileOutputStream.write(hinhsp.getBytes());
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SanPham sanPham = new SanPham(danhMucService.findByMetaTitle(metaTitle), tensp, giasp, mota, gioitinh, hinhsp.getOriginalFilename(), null);

			// nếu mã sản phẩm đã tồn tại thì update
			if (masp != -1) {
				Set<ChiTietSanPham> chitietsSet = new HashSet<>();
				chiTietSanPhamService.findByIdSanPham(masp).forEach(chitietsSet::add);    // convert Iterable to Set

				sanPham.setIdSanPham(masp);
				sanPham.setChitietsanphams(chitietsSet);
			}

			sanPhamService.save(sanPham);
			modelMap.addAttribute("msgThemSanPham", "Successfull");
		}

		return "admin-product";
	}

	@PostMapping(value = "/{metaTitle}", params = "btnThemChiTiet")
	public String themChiTietSanPham(ModelMap modelMap, @PathVariable String metaTitle, @RequestParam int idSanPham, @RequestParam int idSize, @RequestParam int idColor, @RequestParam int quantity) {
		daydu(modelMap, metaTitle);

		ChiTietSanPham chitiet = new ChiTietSanPham();
		chitiet.setMausanpham(mauSanPhamService.findById(idColor));
		chitiet.setSizesanpham(sizeSanPhamService.findById(idSize));
		chitiet.setSanpham(sanPhamService.findById(idSanPham));
		chitiet.setSoLuong(quantity);
		chiTietSanPhamService.save(chitiet);

		return "admin-product";
	}
}
