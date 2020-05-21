package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.ChiTietSanPham;

public interface ChiTietSanPhamService {
	Iterable<ChiTietSanPham> findByIdSanPham(int idSanPham);
	ChiTietSanPham findById(int idChiTiet);
	void save(ChiTietSanPham chiTietSanPham);
}
