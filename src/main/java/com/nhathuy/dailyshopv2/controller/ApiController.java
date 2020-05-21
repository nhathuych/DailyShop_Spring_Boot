package com.nhathuy.dailyshopv2.controller;

import com.nhathuy.dailyshopv2.controller.client.GioHangController;
import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;
import com.nhathuy.dailyshopv2.entity.DanhMucSanPham;
import com.nhathuy.dailyshopv2.entity.SanPham;
import com.nhathuy.dailyshopv2.model.DanhMucDTO;
import com.nhathuy.dailyshopv2.model.GioHang;
import com.nhathuy.dailyshopv2.model.SanPhamDTO;
import com.nhathuy.dailyshopv2.service.ChiTietSanPhamService;
import com.nhathuy.dailyshopv2.service.DanhMucService;
import com.nhathuy.dailyshopv2.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private ChiTietSanPhamService chiTietSanPhamService;

	@Autowired
	private DanhMucService danhMucService;

	@GetMapping("/ThemGioHang")
	public String themGioHang(@RequestParam int idChiTiet, HttpSession httpSession) {
		ChiTietSanPham chitiet = chiTietSanPhamService.findById(idChiTiet);

		if (httpSession.getAttribute(GioHangController.GIOHANG) == null) {
			ArrayList<GioHang> dsGio = new ArrayList<>();

			GioHang gioHang = new GioHang();
			gioHang.setIdSanPham(chitiet.getSanpham().getIdSanPham());
			gioHang.setIdSize(chitiet.getSizesanpham().getIdSize());
			gioHang.setIdMau(chitiet.getMausanpham().getIdMau());
			gioHang.setTenSanPham(chitiet.getSanpham().getTenSanPham());
			gioHang.setGiaTien(chitiet.getSanpham().getGiaTien());
			gioHang.setMau(chitiet.getMausanpham().getMau());
			gioHang.setSize(chitiet.getSizesanpham().getSize());
			gioHang.setSoLuong(1);
			gioHang.setHinhAnh(chitiet.getSanpham().getHinhAnh());
			gioHang.setIdChiTietSanPham(idChiTiet);

			if (chitiet.getSoLuong() > 0) {
				chitiet.setSoLuong(chitiet.getSoLuong() - 1);
				chiTietSanPhamService.save(chitiet);
			}
			dsGio.add(gioHang);
			httpSession.setAttribute(GioHangController.GIOHANG, dsGio);

			return String.valueOf(dsGio.size());
		} else {
			ArrayList<GioHang> dsGio = (ArrayList<GioHang>) httpSession.getAttribute(GioHangController.GIOHANG);
			int vitri = kiemTraSanPhamTrongGio(dsGio, chitiet.getSanpham().getIdSanPham(),
					chitiet.getSizesanpham().getIdSize(), chitiet.getMausanpham().getIdMau());
			if (vitri == -1) {
				GioHang gioHang = new GioHang();
				gioHang.setIdSanPham(chitiet.getSanpham().getIdSanPham());
				gioHang.setIdSize(chitiet.getSizesanpham().getIdSize());
				gioHang.setIdMau(chitiet.getMausanpham().getIdMau());
				gioHang.setTenSanPham(chitiet.getSanpham().getTenSanPham());
				gioHang.setGiaTien(chitiet.getSanpham().getGiaTien());
				gioHang.setMau(chitiet.getMausanpham().getMau());
				gioHang.setSize(chitiet.getSizesanpham().getSize());
				gioHang.setSoLuong(1);
				gioHang.setHinhAnh(chitiet.getSanpham().getHinhAnh());
				gioHang.setIdChiTietSanPham(idChiTiet);

				if (chitiet.getSoLuong() > 0) {
					chitiet.setSoLuong(chitiet.getSoLuong() - 1);
					chiTietSanPhamService.save(chitiet);
				}
				dsGio.add(gioHang);
			} else {
				int soLuongMoi = dsGio.get(vitri).getSoLuong() + 1;
				dsGio.get(vitri).setSoLuong(soLuongMoi);
			}

			return String.valueOf(dsGio.size());
		}
	}

	// kiểm tra sản phẩm đã tồn tài trong giỏ hàng chưa
	private int kiemTraSanPhamTrongGio(ArrayList<GioHang> dsGio, int masp, int masize, int mamau) {
		for (int i = 0; i < dsGio.size(); i++) {
			if (dsGio.get(i).getIdSanPham() == masp && dsGio.get(i).getIdSize() == masize && dsGio.get(i).getIdMau() == mamau) {
				return i;
			}
		}
		return -1;
	}

	@GetMapping("/XoaGioHang")
	public String xoaGioHang(@RequestParam int idChiTiet, HttpSession httpSession) {
		ArrayList<GioHang> gioHangs = null;

		if (httpSession.getAttribute("giohang") != null) {
			gioHangs = (ArrayList<GioHang>) httpSession.getAttribute("giohang");
			for (GioHang sp : gioHangs) {
				if (sp.getIdChiTietSanPham() == idChiTiet) {
					ChiTietSanPham chitiet = chiTietSanPhamService.findById(idChiTiet);
					chitiet.setSoLuong(chitiet.getSoLuong() + sp.getSoLuong());
					gioHangs.remove(sp);

					return String.valueOf(gioHangs.size());
				}
			}
		}

		return "notok"; // not ok
	}

	@PostMapping(value = "/pagination", produces = "text/plain; charset=utf-8")
	public String pagination(@RequestParam int index, @RequestParam int idDanhMuc) {
		int numOfProducts = 4;
		Iterable<SanPham> sanphams = sanPhamService.findByIdDanhMuc(idDanhMuc, numOfProducts * (index - 1), numOfProducts * index);

		StringBuilder html = new StringBuilder();
		//for (SanPham sp : sanphams) { tự nhiên trang thứ 2 load 7 sản phẩm ???????
		Iterator<SanPham> iterator = sanphams.iterator();
		for (int i = 0; i < numOfProducts; ++i) {
			if (!iterator.hasNext()) {
				break;
			}
			SanPham sp = iterator.next();
			html.append(String.format("<tr id='tr%s'>", sp.getIdSanPham()));
			html.append(String.format("<td><img src='/img/product/%s' style='height: 60px; width: 50px'></td>", sp.getHinhAnh()));
			html.append(String.format("<td>%s</td>", sp.getIdSanPham()));
			html.append(String.format("<td>%s</td>", sp.getTenSanPham()));
			html.append(String.format("<td>%s</td>", sp.getGiaTien()));
			html.append("<td>");
			html.append(String.format("<button data-id='%s' class='btnUpdateProduct btn btn-info'>Update</button>", sp.getIdSanPham()));
			html.append(String.format("<button data-id='%s' class='btnDetail btn btn-danger' data-toggle='modal' data-target='#updateModal'>Detail</button>", sp.getIdSanPham()));
			html.append(String.format("<button data-id='%s' class='btnXoaSanPham btn btn-warning'>Delete</button>", sp.getIdSanPham()));
			html.append("</td>");
			html.append("</tr>");
		}

		return html.toString();
	}

	@PostMapping(value = "/DoRaForm", produces = "application/json; charset=utf-8")
	public SanPhamDTO doDuLieuRaForm(@RequestParam int idSanPham) {
		SanPhamDTO sanphamDTO = new SanPhamDTO();
		SanPham sanPham = sanPhamService.findById(idSanPham);

		// set danh sách chi tiết sản phẩm
		Set<ChiTietSanPham> dsChiTiet = new HashSet<>();

		DanhMucSanPham danhmuc = new DanhMucSanPham();
		danhmuc.setIdDanhMuc(sanPham.getDanhmucsanpham().getIdDanhMuc());
		danhmuc.setMetaTitle(sanPham.getDanhmucsanpham().getMetaTitle());
		danhmuc.setTenDanhMuc(sanPham.getDanhmucsanpham().getTenDanhMuc());

//		sanPham.setChitietsanphams(dsChiTiet);	////////////////////////////////////////////////////////////
		sanphamDTO.setIdSanPham(idSanPham);
		sanphamDTO.setTenSanPham(sanPham.getTenSanPham());
		sanphamDTO.setDanhMucSanPham(danhmuc);
		sanphamDTO.setGiaTien(sanPham.getGiaTien());
		sanphamDTO.setGioiTinh(sanPham.getGioiTinh());
		sanphamDTO.setHinhAnh(sanPham.getHinhAnh());
		sanphamDTO.setMoTa(sanPham.getMoTa());

		return sanphamDTO;
	}

	@PostMapping("/DoRaTableDetaild")
	public String doRaTableDetail(@RequestParam int idSanPham) {
		Iterable<ChiTietSanPham> chitiets = chiTietSanPhamService.findByIdSanPham(idSanPham);
		StringBuilder html = new StringBuilder();

		for (ChiTietSanPham ct : chitiets) {
			html.append("<tr>");
			html.append(String.format("<td>%s</td>", ct.getSizesanpham().getSize()));
			html.append(String.format("<td>%s</td>", ct.getMausanpham().getMau()));
			html.append(String.format("<td>%s</td>", ct.getSoLuong()));
			html.append("</tr>");
		}

		return html.toString();
	}

	@PostMapping("/XoaSanPham")
	public String xoaSanPham(@RequestParam int idSanPham) {
		sanPhamService.deleteById(idSanPham);
		return "ok";
	}

	@PostMapping(value = "/DoRaFormDanhMuc", produces = "application/json; charset=utf-8")
	public DanhMucDTO doRaFormDanhMuc(@RequestParam int idDanhMuc) {
		DanhMucSanPham danhmuc = danhMucService.findById(idDanhMuc);
		DanhMucDTO danhMucDTO = new DanhMucDTO();
		danhMucDTO.setIdDanhMuc(idDanhMuc);
		danhMucDTO.setTenDanhMuc(danhmuc.getTenDanhMuc());
		danhMucDTO.setMetaTitle(danhmuc.getMetaTitle());
		danhMucDTO.setStatus(danhmuc.getStatus());

		return danhMucDTO;
	}
}
