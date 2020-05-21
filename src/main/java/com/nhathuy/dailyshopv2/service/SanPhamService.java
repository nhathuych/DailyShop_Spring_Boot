package com.nhathuy.dailyshopv2.service;

import com.nhathuy.dailyshopv2.entity.SanPham;

public interface SanPhamService {
	Iterable<SanPham> findSanPham(int startPosition, int maxResult);
	Iterable<SanPham> findByIdDanhMuc(int idDanhMuc, int startPosition, int maxResult);
	int countByIdDanhMuc(int idDanhMuc);
	Iterable<SanPham> findByTenSanPhamContains(String key);
	SanPham findById(int id);
	void save(SanPham sanPham);
	void deleteById(Integer id);
}
