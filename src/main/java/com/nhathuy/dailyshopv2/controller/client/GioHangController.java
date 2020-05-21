package com.nhathuy.dailyshopv2.controller.client;

import com.nhathuy.dailyshopv2.entity.ChiTietHoaDon;
import com.nhathuy.dailyshopv2.entity.ChiTietHoaDonId;
import com.nhathuy.dailyshopv2.entity.HoaDon;
import com.nhathuy.dailyshopv2.model.GioHang;
import com.nhathuy.dailyshopv2.service.ChiTietHoaDonService;
import com.nhathuy.dailyshopv2.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GioHangController {
	public static final String GIOHANG = "giohang";

	@Autowired
	private HoaDonService hoaDonService;

	@Autowired
	private ChiTietHoaDonService chiTietHoaDonService;

	@GetMapping("/cart")
	public String cart(HttpSession httpSession, ModelMap modelMap) {
		List<GioHang> gioHangs = null;
		if (httpSession.getAttribute(GIOHANG) != null) {
			gioHangs = (List<GioHang>) httpSession.getAttribute(GIOHANG);
			modelMap.addAttribute("dsSanPham", gioHangs);
			modelMap.addAttribute("total", gioHangs.stream().mapToInt(gioHang -> gioHang.getGiaTien() * gioHang.getSoLuong()).sum());
		}
		return "cart";
	}

	@PostMapping(value = "/cart", params = "btnUpdateCart")
	public String updateCart(HttpSession httpSession, ModelMap modelMap, @RequestParam Integer[] soluongsp) {
		List<GioHang> gioHangs = null;
		int lenght = soluongsp.length;
		int total = 0;

		if (httpSession.getAttribute("giohang") != null) {
			gioHangs = (List<GioHang>) httpSession.getAttribute(GIOHANG);
			for (int i = 0; i < lenght; i++) {
				gioHangs.get(i).setSoLuong(soluongsp[i]);
				total += gioHangs.get(i).getGiaTien() * soluongsp[i];
			}
		}

		modelMap.addAttribute("dsSanPham", gioHangs);
		modelMap.addAttribute("total", total);

		return "cart";
	}

	@GetMapping("/checkout")
	public String Default(HttpSession httpSession, ModelMap modelMap) {
		if (httpSession.getAttribute(GIOHANG) == null || ((ArrayList<GioHang>) httpSession.getAttribute(GIOHANG)).isEmpty()) {
			modelMap.addAttribute("msgGioHang", "Giỏ hàng của bạn đang trống. Xin quay lại sau!");
		} else {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute(GIOHANG);
			int total = 0;
			for (GioHang gio : gioHangs) {
				total += gio.getGiaTien() * gio.getSoLuong();
			}
			modelMap.addAttribute("dsSanPham", gioHangs);
			modelMap.addAttribute("total", total);
		}

		return "checkout";
	}

	@PostMapping(value = "/checkout", params = "btnOrder")
	public String ThemHoaDon(HttpSession httpSession, ModelMap modelMap, @RequestParam String fullName, @RequestParam String phoneNumber,
							 @RequestParam String address, @RequestParam String note) {
		if (fullName.isEmpty()) {
			modelMap.addAttribute("msgGioHang", "Không được để trống tên khách hàng");
		} else if (phoneNumber.isEmpty()) {
			modelMap.addAttribute("msgGioHang", "Không được để trống số điện thoại");
		} else if (address.isEmpty()) {
			modelMap.addAttribute("msgGioHang", "Không được để trống địa chỉ giao hàng");
		} else {
			if (httpSession.getAttribute(GIOHANG) != null || !((List<GioHang>) httpSession.getAttribute(GIOHANG)).isEmpty()) {
				List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute(GIOHANG);
				HoaDon hoadon = new HoaDon(fullName, phoneNumber, address, note, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()), false);

				hoaDonService.save(hoadon);
				for (GioHang sp : gioHangs) {
					ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId(hoadon.getIdHoaDon(), sp.getIdChiTietSanPham());

					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
					chiTietHoaDon.setId(chiTietHoaDonId);
					chiTietHoaDon.setGiaTien(sp.getGiaTien());
					chiTietHoaDon.setSoLuong(sp.getSoLuong());

					chiTietHoaDonService.save(chiTietHoaDon);
				}
				// đặt mua và lưu hóa đơn thành công thì xóa giỏ hàng
				httpSession.removeAttribute(GIOHANG);
				modelMap.addAttribute("msgGioHang", "Đặt mua thành công!");
			} else {
				modelMap.addAttribute("msgGioHang", "Giỏ hàng trống!");
			}
		}

		return "checkout";
	}
}
